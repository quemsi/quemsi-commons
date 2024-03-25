package com.biddflux.commons.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;

public class Exceptions {
	private HttpStatus status;
	private String messageId;
	private Map<String, Object> extra;
	private Throwable cause;
	private Exceptions() {}
	
	public static Exceptions create(HttpStatus status, String messageId) {
		Exceptions exs = new Exceptions();
		exs.status = status;
		exs.messageId = messageId;
		return exs;
	}
	
	public static Exceptions server(String messageId){
		return create(HttpStatus.INTERNAL_SERVER_ERROR, messageId);
	}
	public static Exceptions notFound(String messageId){
		return create(HttpStatus.NOT_FOUND, messageId);
	}
	public static Exceptions badRequest(String messageId) {
		return create(HttpStatus.BAD_REQUEST, messageId);
	}
	public static Exceptions notAcceptable(String messageId) {
		return create(HttpStatus.NOT_ACCEPTABLE, messageId);
	}
	public static Exceptions auth(String messageId) {
		return create(HttpStatus.UNAUTHORIZED, messageId);
	}
	public static Exceptions notImplemented() {
		return create(HttpStatus.SERVICE_UNAVAILABLE, "not-implemented");
	}
	public static Exceptions notSupported(String messageId) {
		return create(HttpStatus.SERVICE_UNAVAILABLE, messageId);
	}
	public BaseRuntimeException get() {
		return new BaseRuntimeException(this.status, messageId, extra, cause); 
	}
	public void throwIt() {
		throw get(); 
	}
	public ExceptionSupplier<BaseRuntimeException> supplier() {
		return () -> {
			throw get();
			};
	}
	
	public Exceptions withExtra(String key, Object value) {
		if(this.extra == null) {
			this.extra = new HashMap<>();
		}
		this.extra.put(key, value);
		return this;
	}
	public Exceptions withCause(Throwable cause) {
		this.cause = cause;
		return this;
	}
	
	public static interface ExceptionSupplier<T extends Exception> extends Supplier<T> {
		default void throwIt() {
			get();
		}
	}
	@FunctionalInterface
	public static interface FunctionWithException<T, R, E extends Exception> {
	    R apply(T t) throws E;
	}
	public static <T, R, E extends Exception> Function<T, R> wrapFunction(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw Exceptions.server("exception-occured").withCause(e).get();
            }
        };
	}
	@FunctionalInterface
	public static interface ConsumerWithException<T, E extends Exception> {
	    void accept(T t) throws E;
	}
	public static <T, E extends Exception> Consumer<T> wrapConsumer(ConsumerWithException<T, E> fe) {
        return arg -> {
            try {
                fe.accept(arg);
            } catch (Exception e) {
                throw Exceptions.server("exception-occured").withCause(e).get();
            }
        };
	}
}
