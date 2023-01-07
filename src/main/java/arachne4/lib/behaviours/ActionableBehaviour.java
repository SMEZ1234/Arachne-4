package arachne4.lib.behaviours;

import arachne4.lib.sequences.ActionConductor;
import arachne4.lib.sequences.Actionable;
import arachne4.lib.sequences.actions.Action;

public class ActionableBehaviour implements Behaviour, Completable {
    protected final ActionConductor conductor;
    protected final Actionable actionableToRun;

    protected Action currentAction;

    protected ActionableBehaviour(Actionable actionableToRun) {
        this.conductor = new ActionConductor();
        this.actionableToRun = actionableToRun;
    }

    @Override
    public void onEnterMode() {
        currentAction = conductor.add(actionableToRun);
    }

    @Override
    public void run() {
        conductor.run();
    }

    @Override
    public void onLeaveMode() {
        conductor.clear();
    }

    public boolean isComplete() {
        return currentAction.hasFinished();
    }
}
