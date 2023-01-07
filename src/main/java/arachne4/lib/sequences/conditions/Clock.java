package arachne4.lib.sequences.conditions;

import java.util.function.BooleanSupplier;

import arachne4.lib.units.Duration;
import arachne4.lib.units.Milliseconds;

public class Clock
{
	private Clock() {}
	
	public static BooleanSupplier delay(Duration duration) {
		return new RepeatableCondition() {
			long endTime;
			
			@Override
			protected void initialize() {
				endTime = System.currentTimeMillis() + (long) duration.asRaw(Milliseconds::convert);
			}
			
			@Override
			protected boolean condition() {
				return System.currentTimeMillis() >= endTime;
			}
		};
	}
}
