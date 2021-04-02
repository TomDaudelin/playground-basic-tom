import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.util.StopWatch;

@Interceptor
public class IClientInterceptor {
	
	private StopWatch stop_watch;
	
	public IClientInterceptor() {
		this.stop_watch = new StopWatch();
	}
	
	@Hook(Pointcut.CLIENT_REQUEST)
	public void startStopWatch() {
		this.stop_watch.startTask("Search for patient");
	}
	
	@Hook(Pointcut.CLIENT_RESPONSE)
	public void stopStopWatch() {
	    this.stop_watch.endCurrentTask();
	}
	
	public long requestStopWatch() {
		return this.stop_watch.getMillis();
	}
}
