package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = enf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
           /* Member findMember =  em.find(Member.class, 1L);
            findMember.setName("HelloJPA");*/

            List<Member> result = em.createQuery("select m FROM Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("Member.name = " + member.getName());
            }



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear();
        }
        enf.close();
    }
}
