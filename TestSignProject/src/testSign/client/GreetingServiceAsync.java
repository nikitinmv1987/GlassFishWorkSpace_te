package testSign.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void pushTestDoc(TestDoc doc, AsyncCallback<String> callback) throws IllegalArgumentException;;
	void getTestDoc(AsyncCallback<TestDoc> callback);
}
