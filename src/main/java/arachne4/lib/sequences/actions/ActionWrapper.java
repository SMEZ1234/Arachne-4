package arachne4.lib.sequences.actions;

import arachne4.lib.function.BooleanPredicate;

public abstract class ActionWrapper extends HostAction
{
	protected Action currentAction;
	
	public ActionWrapper(HostAction host) {
		super(host);
	}
	
	public ActionWrapper(HostAction host, BooleanPredicate conditionModifier) {
		super(host, conditionModifier);
	}
	
	protected abstract Action getNextAction(boolean isFirst);

	@Override
	protected void initialize() {
		currentAction = getNextAction(true);
	}
	
	@Override
	protected void execute() {
		ActionState subState = null;
		
		while(currentAction != null && (subState = currentAction.act()) == ActionState.FINISHED) {
			currentAction = getNextAction(false);
		}
		
		if(subState == ActionState.INTERRUPTED) state = ActionState.INTERRUPTED;
	}
	
	@Override
	protected boolean isFinished() {
		return currentAction == null;
	}
	
	@Override
	protected void interruptChildren() {
		if(currentAction != null) currentAction.handleInterruptFromHost();
	}
	
	@Override
	protected void handleInterruptFromChild(Action interruptSource) {
		interruptSelf();
		interruptHost();
	}
}
