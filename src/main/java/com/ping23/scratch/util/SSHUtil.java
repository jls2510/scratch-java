package com.ping23.scratch.util;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SSHUtil.class);

	public static String sshRemoteCommand(String username, String password, String host, int port, String command)
			throws Exception {

		Session session = null;
		ChannelExec channel = null;

		String responseString;
		try {
			session = new JSch().getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			channel.setOutputStream(responseStream);
			channel.connect();

			while (channel.isConnected()) {
				Thread.sleep(100);
			}

			responseString = new String(responseStream.toByteArray());
		} finally {
			if (session != null) {
				session.disconnect();
			}
			if (channel != null) {
				channel.disconnect();
			}
		}
		
		//LOG.debug(responseString);
		return responseString;
	}

}
