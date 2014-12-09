package com.us.os.qd.template;

public interface ServiceTemplate<T> {
	public abstract void save(T model);
	public abstract T findById(Integer id);
	public abstract void update(T model);
}
