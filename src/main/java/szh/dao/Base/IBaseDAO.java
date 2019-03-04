package szh.dao.Base;

import szh.bean.BaseBean;

import java.io.Serializable;
import java.util.Collection;

public interface IBaseDAO<T extends BaseBean> {

    public T getById(Long id);

    public T getByUid(String uid);

    public T add(T t);

    public Collection<T> addList(Collection<T> list);

    public T update(T t);

    public void updateList(Collection<T> list);

    public void deleteById(Long id);

    public void deleteByUid(String uid);

    public void deleteByIds(Collection<Long> ids);

    public void deleteByUids(Collection<String> uids);

}
