package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Student;
import entidades.Tuition;

public class Ud4TareaAprendizaje4aEjercicio4 {

	/**
	 * 4. OneToOne unidireccional entre entidades Student y Tuition (matrícula).
	 * Borra un Tuition y su Student asociado
	 */
	public static void main(String[] args) {

		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Student.class )
			    .addAnnotatedClass( Tuition.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		
		try {			
		
			System.out.println("Borrando un nuevo Student y en cascada su Tuition asociado");
			
			int student_id = 11;
			
			Student tempStudent = session.get(Student.class, student_id);
			// comienza la transacción
			session.beginTransaction();
		
			// borra el Student y con CascadeType.ALL termina borrando su Tuition
			session.remove(tempStudent);
			
			// hace commit de la transaccion
			session.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
	
}




