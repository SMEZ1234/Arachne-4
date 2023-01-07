package arachne4.lib.sequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arachne4.lib.function.BooleanPredicate;
import arachne4.lib.sequences.actions.Action;
import arachne4.lib.sequences.actions.ActionWrapper;
import arachne4.lib.sequences.actions.HostAction;

public class Sequence implements Untilable
{
	protected List<Actionable> actionables;
	
	public Sequence(Actionable... actionables) {
		this.actionables = new ArrayList<Actionable>(Arrays.asList(actionables));
	}
	
	@Override
	public Action asAction(HostAction host, BooleanPredicate conditionModifier) {
		return new ActionWrapper(host, conditionModifier) {
			int counter;
			
			@Override
			protected Action getNextAction(boolean isFirst) {
				if(isFirst) counter = 0;
				else counter++;
				
				if(counter < Sequence.this.actionables.size()) return Sequence.this.actionables.get(counter).asAction(this);
				return null;
			}
		};
	}
}
