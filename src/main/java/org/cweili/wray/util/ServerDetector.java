/**
 * 
 */
package org.cweili.wray.util;

/**
 * 检测应用服务器类型
 * 
 * @author cweili
 * @version 2013-4-12 下午7:28:30
 * 
 */
public class ServerDetector {

	private static final String[] SERVERS = new String[] {
			"/org/apache/catalina/startup/Bootstrap.class",

			"/org/jboss/Main.class",

			"/org/mortbay/jetty/Server.class",

			"/org/eclipse/jetty/server/Server.class",

			"/weblogic/Server.class",

			"/com/ibm/websphere/product/VersionInfo.class",

			"/org/objectweb/jonas/server/Server.class",

			"/oracle/jsp/oc4jutil/Oc4jUtil.class",

			"/com/evermind/server/ApplicationServer.class",

			"/com/pramati/Server.class",

			"/com/caucho/server/resin/Resin.class",

			"/com/tcc/Main.class",

			"/com/iplanet/ias/tools/cli/IasAdminMain.class",

			"/com/sun/enterprise/cli/framework/CLIMain.class",

			"/org/apache/geronimo/system/main/Daemon.class" };

	public static final int NOT_DETECTED = 0;
	public static final int TOMCAT = 1;
	public static final int JBOSS = 2;
	public static final int JETTY = 3;
	public static final int WEBLOGIC = 4;
	public static final int WEBSPHERE = 5;
	public static final int JONAS = 6;
	public static final int OC4J = 7;
	public static final int ORION = 8;
	public static final int PRAMATI = 9;
	public static final int RESIN = 10;
	public static final int REXIP = 11;
	public static final int SUN7 = 12;
	public static final int SUN8 = 13;
	public static final int GERONIMO = 14;

	private static ServerDetector instance;

	private ServerDetector() {
	}

	public static ServerDetector getInstance() {
		if (null == instance) {
			instance = new ServerDetector();
		}
		return instance;
	}

	public int detect() {
		for (int i = 0; i < SERVERS.length; i++) {
			if (null != this.getClass().getResource(SERVERS[i])) {
				switch (i) {
				case 0:
					return TOMCAT;
				case 1:
					return JBOSS;
				case 2:
				case 3:
					return JETTY;
				case 4:
					return WEBLOGIC;
				case 5:
					return WEBSPHERE;
				case 6:
					return JONAS;
				case 7:
					return OC4J;
				case 8:
					return ORION;
				case 9:
					return PRAMATI;
				case 10:
					return RESIN;
				case 11:
					return REXIP;
				case 12:
					return SUN7;
				case 13:
					return SUN8;
				case 14:
					return GERONIMO;
				}
			}
		}
		return NOT_DETECTED;
	}
}
