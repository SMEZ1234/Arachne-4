package arachne4.lib.sequences;

import java.util.function.BooleanSupplier;

import arachne4.lib.function.BooleanPredicate;
import arachne4.lib.sequences.actions.Action;
import arachne4.lib.sequences.actions.ActionWrapper;
import arachne4.lib.sequences.actions.Fork;
import arachne4.lib.sequences.actions.HostAction;
import arachne4.lib.sequences.conditions.Clock;
import arachne4.lib.units.Duration;

@FunctionalInterface
public interface Actionable {
	Action asAction(HostAction host);
	
	// --------------------
	// Basic actionables
	// --------------------
	
	public static Actionable DO_NOTHING() {
		return Action::new;
	}
	
	public static Actionable DO(Runnable task) {
		return (host) -> new Action(host) {
			@Override
			protected void initialize() {
				task.run();
			};
		};
	}
	
	public static Untilable SEQUENCE(Actionable... actionables) {
		return new Sequence(actionables);
	}

	// --------------------
	// Waiting
	// --------------------
	
	public static Untilable WAIT() {
		return (host, conditionModifier) -> new Action(host, conditionModifier) {
			@Override
			protected boolean isFinished() {
				return false;
			};
		};
	}
	
	public static Untilable WAIT(Duration delay) {
		return (host, conditionModifier) -> new Action(host, conditionModifier) {
			BooleanSupplier condition = Clock.delay(delay);

			@Override
			protected boolean isFinished() {
				return condition.getAsBoolean();
			};
		};
	}
	
	// --------------------
	// Multi-actions
	// --------------------
	
	public static Split SPLIT(Actionable actionable) {
		return new Split(actionable);
	}
	
	public static final class Split implements Untilable {
		protected final Fork fork;
		
		protected Split(Actionable actionable) {
			this.fork = new Fork(actionable);
		}
		
		public final Split AND(Actionable actionable) {
			fork.add(actionable);
			return this;
		}

		@Override
		public final Action asAction(HostAction host, BooleanPredicate conditionModifier) {
			return fork.asAction(host, conditionModifier);
		}
	}
	
	// --------------------
	// Repetition
	// --------------------
	
	public static Repeat REPEAT(Runnable task) {
		return new Repeat(task);
	}
	
	public static RepeatActionable REPEAT(Actionable actionable) {
		return new RepeatActionable(actionable);
	}
	
	public static final class Repeat implements Untilable {
		protected final Runnable task;

		protected Repeat(Runnable task) {
			this.task = task;
		}
		
		@Override
		public Action asAction(HostAction host, BooleanPredicate conditionModifier) {
			return new Action(host, conditionModifier) {
				@Override
				protected void execute() {
					Repeat.this.task.run();
				}
				
				@Override
				protected boolean isFinished() {
					return false;
				}
			};
		}
	}
	
	public static final class RepeatActionable implements Actionable {
		protected final Actionable actionable;
		
		protected RepeatActionable(Actionable actionable) {
			this.actionable = actionable;
		}
		
		@Override
		public Action asAction(HostAction host) {
			return new ActionWrapper(host) {
				@Override
				protected Action getNextAction(boolean isFirst) {
					return RepeatActionable.this.actionable.asAction(this);
				}
			};
		}
	}
	
	// --------------------
	// Selection
	// --------------------
	
	public static If IF(BooleanSupplier condition) {
		return new If(condition);
	}
	
	public static final class If {
		protected final BinarySelector selector;
		protected final BooleanSupplier condition;
		
		protected If(BooleanSupplier condition) {
			this(new BinarySelector(), condition);
		}
		
		protected If(BinarySelector selector, BooleanSupplier condition) {
			this.selector = selector;
			this.condition = condition;
		}
		
		public final IfThen THEN(Actionable actionable) {
			return new IfThen(selector.addOption(condition, actionable));
		}
	}
	
	public static final class IfThen implements Actionable {
		protected final BinarySelector selector;
		
		protected IfThen(BinarySelector selector) {
			this.selector = selector;
		}

		@Override
		public final Action asAction(HostAction host) {
			return selector.asAction(host);
		}
		
		public final If ELSE_IF(BooleanSupplier condition) {
			return new If(selector, condition);
		}
		
		public final Actionable ELSE(Actionable actionable) {
			return selector.setElse(actionable);
		}
	}
}
