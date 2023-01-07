package arachne4.lib.sequences.conditions;

import java.util.function.BooleanSupplier;

public abstract class RepeatableCondition implements BooleanSupplier
{
	protected boolean shouldRepeat, isInitialized;
	
	public RepeatableCondition() {
		this(true);
	}
	
	public RepeatableCondition(boolean shouldRepeat) {
		this.shouldRepeat = shouldRepeat;
		this.isInitialized = false;
	}
	
	@Override
	public boolean getAsBoolean() {
		if(!isInitialized) {
			initialize();
			isInitialized = true;
		}
		
		if(condition()) {
			if(shouldRepeat) {
				reset();
				isInitialized = false;
			}
			return true;
		}
		
		return false;
	}

	protected void initialize() {}
	protected void reset() {}
	
	protected abstract boolean condition();
}
