package SE2.Swimv2.Util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import SE2.Swimv2.Session.GestoreAdminRemote;
import SE2.Swimv2.Session.GestoreLoginRemote;
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Session.GestoreUserRemote;

public class RemoteManager {
	
	public RemoteManager(){
	}	

	public GestoreUserRemote getGestoreUserRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreUser/remote");
		GestoreUserRemote manager = (GestoreUserRemote) obj;
		return manager;
	}
	
	public GestoreRichiesteAmiciziaRemote getGestoreRichiesteAmiciziaRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreRichiesteAmicizia/remote");
		GestoreRichiesteAmiciziaRemote manager = (GestoreRichiesteAmiciziaRemote) obj;
		return manager;
	}
	
	public GestoreLoginRemote getGestoreLoginRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreLogin/remote");
		GestoreLoginRemote manager = (GestoreLoginRemote) obj;
		return manager;
	}
	
	public GestoreAdminRemote getGestoreAdminRemote() throws NamingException{
		Context jndiContext = new InitialContext();
		Object obj = jndiContext.lookup("GestoreAdmin/remote");
		GestoreAdminRemote manager = (GestoreAdminRemote) obj;
		return manager;
	}
}
