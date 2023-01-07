package arachne4.lib.sequences.actions;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import arachne4.lib.function.BooleanPredicate;
import arachne4.lib.sequences.ActionConductor;
import arachne4.lib.sequences.Actionable;
import arachne4.lib.sequences.Untilable;

public class Fork implements Untilable
{
	protected final Set<Actionable> actionables;
	
	public Fork(Actionable... actionables) {
		this.actionables = new LinkedHashSet<Actionable>(Arrays.asList(actionables));
	}
	
	public void add(Actionable actionable) {
		actionables.add(actionable);
	}
	
	public void clear() {
		actionables.clear();
	}
	
	@Override
	public Action asAction(HostAction host, BooleanPredicate conditionModifier) {
		return new ForkAction(host, conditionModifier, actionables);
	}
	
	protected class ForkAction extends HostAction {
		protected final ActionConductor scheduler;
		
		protected ForkAction(HostAction host, BooleanPredicate conditionModifier, Collection<Actionable> actionables) {
			super(host, conditionModifier);
			
			this.scheduler = new ActionConductor();			
			for(Actionable actionable : actionables) scheduler.add(actionable, this);
		}

		@Override
		protected void execute() {
			scheduler.run();
		}

		@Override
		protected boolean isFinished() {
			return !scheduler.hasActions();
		}

		@Override
		protected void interruptChildren() {
			scheduler.interrupt();
		}

		@Override
		protected void handleInterruptFromChild(Action interruptSource) { /* Do nothing */ }
	}
}
