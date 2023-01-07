package arachne4.lib.sequences.actions;

import arachne4.lib.function.BooleanPredicate;
import arachne4.lib.logging.ArachneLogger;

public class Action {
	protected final HostAction host;
	protected final BooleanPredicate conditionModifier;
	
	protected ActionState state;

	public Action(HostAction host) {
		this(host, BooleanPredicate.identity());
	}
	
	public Action(HostAction host, BooleanPredicate conditionModifier) {
		this.host = host;
		this.conditionModifier = conditionModifier;
		
		this.state = ActionState.PRE_INITIALIZATION;
	}
	
	protected void initialize() {}
	protected void execute() {}
	protected void end() {}
	
	protected void interrupted() {
		end();
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected final boolean isFinishedWithModifiedCondition() {
		return conditionModifier.test(isFinished());
	}
	
	public ActionState act() {
		return state.run(this);
	}
	
	public void interrupt() {
		interruptSelf();
		interruptHost();
	}
	
	protected void interruptHost() {
		if(host == null) return;
		
		host.interruptHost();
		host.interruptSelf();
	}
	
	protected void interruptSelf() {
		interrupted();
		state = ActionState.INTERRUPTED;
	}
	
	protected void handleInterruptFromHost() {
		interruptSelf();
	}
	
	public boolean hasFinished() {
		return state == ActionState.FINISHED;
	}
	
	public enum ActionState	{
		PRE_INITIALIZATION {
			@Override
			protected ActionState run(Action action) {
				action.initialize();
				action.state = RUNNING;
				return RUNNING.run(action);
			}
		},
		RUNNING {
			@Override
			protected ActionState run(Action action) {
				action.execute();
				
				if(action.state == INTERRUPTED) return INTERRUPTED.run(action);
				
				if(action.isFinishedWithModifiedCondition()) {
					action.end();
					action.state = FINISHED;
				}
				
				return action.state;
			}
		},
		FINISHED {
			@Override
			protected ActionState run(Action action) {
				ArachneLogger.getInstance().error("Tried to run an action that has already finished! Doing nothing.");
				return FINISHED;
			}
		},
		INTERRUPTED {
			@Override
			protected ActionState run(Action action) {
				action.state = FINISHED;
				return INTERRUPTED;
			}
		};
		
		protected abstract ActionState run(Action action);
	}
}
