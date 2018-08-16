package cn.com.inlee.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MinaTCPServerConfiguration {


	@Value("${listen.port:8801}")
	private int PORT;

	@Value("${listen.maxbuffer:10240}")
	private int MAX_BUFFER;

	@Autowired(required = true)
	private ProtocolCodecFactory protocolCodecFactory;

	@Autowired(required = true)
	private IoHandlerAdapter ioHandlerAdapter;

	@Bean
	public SocketAcceptor startServer() {
		SocketAcceptor acceptor = new NioSocketAcceptor();
		// 设置解析器
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

		chain.addLast("codec", new ProtocolCodecFilter(protocolCodecFactory));
		chain.addLast("logger", new LoggingFilter());

		// 设置读取数据的缓冲区大小
		acceptor.getSessionConfig().setReadBufferSize(MAX_BUFFER);
		// 读写通道10秒内无操作进入空闲状态
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
		acceptor.setHandler(ioHandlerAdapter);
		try {
			acceptor.bind(new InetSocketAddress(PORT));
		}
		catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		log.info("Serial Server Started : {}", PORT);

		return acceptor;
	}
	
}
