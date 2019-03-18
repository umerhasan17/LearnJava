package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.Collections;

public interface Scheduler {

	/**
	 * Returns the next thread to be executed for the
	 * given program
	 *
	 * @param program
	 * 			the program for which a scheduling decision is required
	 * @return the thread to be scheduled next
	 * @throws
	 */
	public int chooseThread(ConcurrentProgram program) throws DeadlockException;

	public default int getMinThreadID(ConcurrentProgram program) {
		assert !program.getEnabledThreadIds().isEmpty();
		return Collections.min(program.getEnabledThreadIds());
	}
}
