package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class RoundRobinScheduler implements Scheduler {

  private boolean firstTime = false;
  private int previousID;

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    if (program.getEnabledThreadIds().isEmpty()) {
      throw new DeadlockException();
    } else {
      if (!firstTime) {
        // return smallest id
        firstTime = true;
        previousID = getMinThreadID(program);
        return previousID;
      } else {
        // return smallest that's bigger than previous
        Set<Integer> filtered = program.getEnabledThreadIds()
                .stream()
                .filter( id -> id > previousID)
                .collect(Collectors.toSet());
        if (filtered.size() == 0) {
          previousID = getMinThreadID(program);
          return previousID;
        }
        previousID = Collections.min(filtered);
        return previousID;
      }
    }
  }
}
