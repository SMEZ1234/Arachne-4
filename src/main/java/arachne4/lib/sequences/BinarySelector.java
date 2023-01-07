package arachne4.lib.sequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;

import arachne4.lib.sequences.actions.Action;
import arachne4.lib.sequences.actions.ActionWrapper;
import arachne4.lib.sequences.actions.HostAction;

public class BinarySelector implements Actionable
{
	protected final List<BinaryOption> options;
	protected Actionable actionableAsElse;
	
	public BinarySelector() {
		this(null);
	}
	
	public BinarySelector(Actionable actionableAsElse, BinaryOption... options) {
		this.actionableAsElse = actionableAsElse;
		this.options = new ArrayList<BinaryOption>(Arrays.asList(options));
	}
	
	public BinarySelector addOption(BooleanSupplier condition, Actionable actionable) {
		options.add(new BinaryOption(condition, actionable));
		return this;
	}
	
	public BinarySelector setElse(Actionable actionable) {
		this.actionableAsElse = actionable;
		return this;
	}

	@Override
	public Action asAction(HostAction host) {
		return new ActionWrapper(host) {
			@Override
			protected Action getNextAction(boolean isFirst) {
				if(!isFirst) return null;
				
				for(BinaryOption option : options) {
					if(option.getAsBoolean()) return option.asAction(this);
				}
				
				return actionableAsElse != null ? actionableAsElse.asAction(this) : null;
			}
		};
	}
	
	public static class BinaryOption implements Actionable, BooleanSupplier {
		protected final BooleanSupplier condition;
		protected final Actionable actionable;
		
		public BinaryOption(BooleanSupplier condition, Actionable actionable) {
			this.condition = condition;
			this.actionable = actionable;
		}

		@Override
		public boolean getAsBoolean() {
			return condition.getAsBoolean();
		}

		@Override
		public Action asAction(HostAction host) {
			return actionable.asAction(host);
		}
	}
}
