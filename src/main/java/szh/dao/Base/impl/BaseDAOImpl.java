package szh.dao.Base.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import szh.bean.BaseBean;
import szh.dao.Base.IBaseDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SuppressWarnings("unchecked")
public class BaseDAOImpl<T extends BaseBean> implements IBaseDAO<T> {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDAOImpl() {
        // 获取当前new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取第一个类型参数的真实类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(Long id) {
        if (id == null) {
            return null;
        } else {
            return (T) getSession().get(clazz, id);
        }
    }

    @Override
    public T getByUid(String uid) {
        return null;
    }

    @Override
    public T add(T t) {
        return (T) getSession().save(t);
    }

    @Override
    public Collection<T> addList(Collection<T> list) {
        List<T> tempList = null;
        Collection<T> backList = new ArrayList<>();
        if (list != null && list.size() != 0){
            tempList = (List<T>) list;
            T temp = null;
            for (int f = 0; f < tempList.size(); f ++){
                temp = tempList.get(f);
                backList.add((T) getSession().save(temp));
                if (f % 10 == 0){
                    getSession().flush();
                }
            }
            getSession().flush();
        }
        return backList;
    }

    @Override
    public T update(T t) {
        getSession().update(t);
        return t;
    }

    @Override
    public void updateList(Collection<T> list) {
        List<T> tempList = null;
        if (list != null && list.size() != 0){
            tempList = (List<T>) list;
            T temp = null;
            for (int f = 0; f < tempList.size(); f ++){
                temp = tempList.get(f);
                getSession().update(temp);
                if (f % 10 == 0){
                    getSession().flush();
                }
            }
            getSession().flush();
        }
    }

    @Override
    public void deleteById(Long id) {
        Object obj = getById(id);
        if (obj != null) {
            getSession().delete(obj);
        }
    }

    @Override
    public void deleteByUid(String uid) {

    }

    @Override
    public void deleteByIds(Collection<Long> ids) {
        List<Long> templist = null;
        if (ids != null && ids.size() != 0){
            templist = (List<Long>) ids;
            Long tf = null;
            for (int f = 0; f < templist.size(); f ++){
                tf = templist.get(f);
                Object to = getSession().load(clazz, tf);
                getSession().delete(to);

            }
            getSession().flush();
        }
    }

    @Override
    public void deleteByUids(Collection<String> uids) {

    }
}
