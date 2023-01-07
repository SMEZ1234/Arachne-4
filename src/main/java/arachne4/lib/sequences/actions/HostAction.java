package arachne4.lib.sequences.actions;

import arachne4.lib.function.BooleanPredicate;

public abstract class HostAction extends Action {
	public HostAction(HostAction host) {
		super(host);
	}
	
	public HostAction(HostAction host, BooleanPredicate conditionModifier) {
		super(host, conditionModifier);
	}
	
	@Override
	public void interrupt() {
		interruptChildren();
		super.interrupt();
	}
	
	@Override
	protected void handleInterruptFromHost() {
		interruptChildren();
		super.handleInterruptFromHost();
	}
	
	protected abstract void interruptChildren();
	protected abstract void handleInterruptFromChild(Action interruptSource);
}
