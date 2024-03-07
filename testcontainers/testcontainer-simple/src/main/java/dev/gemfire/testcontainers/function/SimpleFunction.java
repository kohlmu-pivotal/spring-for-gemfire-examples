package dev.gemfire.testcontainers.function;

import org.apache.geode.cache.execute.FunctionAdapter;
import org.apache.geode.cache.execute.FunctionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFunction extends FunctionAdapter {
  @Override
  public boolean hasResult() {
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(FunctionContext functionContext) {
    SimpleDateFormat dataFormatter = new SimpleDateFormat("yyyy-MMM-dd");
    functionContext.getResultSender().lastResult("Server time: " + dataFormatter.format(new Date()));
  }

  @Override
  public String getId() {
    return "ServerTimeFunction";
  }
}
