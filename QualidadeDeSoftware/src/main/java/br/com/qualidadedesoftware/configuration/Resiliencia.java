package br.com.qualidadedesoftware.configuration;

import org.testng.ITestResult;
import org.testng.util.RetryAnalyzerCount;

public class Resiliencia extends RetryAnalyzerCount {

	public Resiliencia() {
		setCount(3);
	}

	@Override
	public boolean retryMethod(ITestResult result) {
		if (result.isSuccess()) {
			return false;
		} else {
			return true;
		}
	}
}
