package com.example.be.repository;

import com.example.be.entity.Role;
import com.example.be.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRole(User user) {
        StringBuilder sql = new StringBuilder().append("select r.role_name as roleName from users u join users_roles ur on u.user_id = ur.user_id " +
                "join roles r on r.role_id = ur.role_id");
        sql.append("where 1=1 ");
        if (user.getEmail() != null) {
            sql.append(" and email = :email");
        }

        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if (user.getEmail() != null) {
            query.setParameter("email", user.getEmail());
        }

        query.addScalar("roleName", StandardBasicTypes.STRING);
        query.setResultListTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }
}
