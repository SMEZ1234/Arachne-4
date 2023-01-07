package arachne4.lib.behaviours;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import arachne4.lib.sequences.Actionable;
import arachne4.lib.sequences.Untilable;
import arachne4.lib.sequences.actions.Action;

public class BehaviourManager<BehaviourT extends Behaviour> implements Runnable {
    protected Optional<BehaviourT> currentMode;
    protected Optional<ActionableBehaviour> currentActionMode;

    protected final Supplier<BehaviourT> actionBackingBehaviourSupplier;

    public BehaviourManager(BehaviourT initialMode) {
        this(initialMode, () -> null);
    }

    public BehaviourManager(BehaviourT initialMode, BehaviourT actionBackingBehaviourInstance) {
        this(initialMode, () -> actionBackingBehaviourInstance);
    }

    public BehaviourManager(BehaviourT initialMode, Supplier<BehaviourT> actionBackingBehaviourSupplier) {
        currentMode = Optional.empty();
        currentActionMode = Optional.empty();

        changeToMode(initialMode);

        this.actionBackingBehaviourSupplier = actionBackingBehaviourSupplier;
    }

    public void run() {
        currentMode.ifPresent(Behaviour::run);
        currentActionMode.ifPresent(Behaviour::run);
    }

    public void changeToMode(BehaviourT mode) {
        leaveCurrentMode();

        currentMode = Optional.ofNullable(mode);
        currentActionMode = Optional.empty();

        enterCurrentMode();
    }

    public Completable changeToMode(Actionable actionableAsMode) {
        leaveCurrentMode();

        currentMode = Optional.ofNullable(actionBackingBehaviourSupplier.get());

        var nonNullActionMode = new ActionableBehaviour(actionableAsMode);
        currentActionMode = Optional.of(nonNullActionMode);

        enterCurrentMode();

        return nonNullActionMode;
    }

    public Untilable doActionAsModeUntilComplete(Actionable actionable) {
        return (host, conditionModifier) -> new Action(host, conditionModifier) {
            Completable mode;

            @Override
            protected void initialize() {
                mode = changeToMode(actionable);
            }

            @Override
            protected boolean isFinished() {
                return mode.isComplete() || currentActionMode.orElse(null) != mode;
            }
        };
    }

    protected void enterCurrentMode() {
        currentMode.ifPresent(Behaviour::onEnterMode);
        currentActionMode.ifPresent(Behaviour::onEnterMode);
    }

    protected void leaveCurrentMode() {
        currentActionMode.ifPresent(Behaviour::onLeaveMode);
        currentMode.ifPresent(Behaviour::onLeaveMode);
    }

    public Optional<BehaviourT> getCurrentMode() {
        return currentMode;
    }

    public Optional<Completable> getCompletable() {
        return currentActionMode.map(Function.identity());
    }
}
