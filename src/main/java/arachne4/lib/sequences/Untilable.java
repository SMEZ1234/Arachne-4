package arachne4.lib.sequences;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import arachne4.lib.function.BooleanPredicate;
import arachne4.lib.sequences.actions.Action;
import arachne4.lib.sequences.actions.HostAction;

@FunctionalInterface
public interface Untilable extends Actionable {
	Action asAction(HostAction host, BooleanPredicate conditionModifier);
	
	@Override
	default Action asAction(HostAction host) {
		return asAction(host, (value) -> value);
	}
	
	default Actionable UNSAFE_UNTIL(BooleanSupplier condition) {
		return UNSAFE_UNTIL((value) -> condition.getAsBoolean());
	}
	
	default Actionable UNSAFE_UNTIL(BooleanPredicate conditionModifier) {
		return (host) -> asAction(host, conditionModifier);
	}
	
	default Actionable UNTIL(Supplier<BooleanPredicate> conditionModifierSupplier) {
		return (host) -> asAction(host, conditionModifierSupplier.get());
	}
}
