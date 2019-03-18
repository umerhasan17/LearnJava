package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.Stmt;
import concurrency.statements.WaitStmt;

import java.util.Set;
import java.util.stream.Collectors;

public class FewestWaitsScheduler implements Scheduler {
  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    if (program.getEnabledThreadIds().isEmpty()) {
      throw new DeadlockException();
    } else {
      int minWaitsThreadId = Integer.MAX_VALUE;
      int minWaits = Integer.MAX_VALUE;
      for (int id : program.getEnabledThreadIds()) {
        Set<? extends Stmt> filtered = program.remainingStatements(id)
                                      .stream()
                                      .filter(stmt -> stmt instanceof WaitStmt)
                                      .collect(Collectors.toSet());
        if ((id < minWaitsThreadId && filtered.size() == minWaits) ||
                filtered.size() < minWaits) {
          minWaits = filtered.size();
          minWaitsThreadId = id;
        }

      }
      return minWaitsThreadId;
    }
  }
}
