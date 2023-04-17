//package ma.enset.gestionabonnement;
//
//import ma.enset.gestionabonnement.entities.Client;
//import ma.enset.gestionabonnement.repositories.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.data.domain.PageRequest;
//
//public class JpaApplication implements CommandLineRunner {
//    @Autowired
//    private ClientRepository clientRepository;
//    public static void main(String[] args) {
//        SpringApplication.run(GestionAbonnementApplication.class, args);
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 100; i++) {
//            clientRepository.save(new Client(null, "douhi",));
//        }
//        Page<Patient> patients = patientRepository.findAll(PageRequest.of(1, 5));
//        System.out.println("total pages : " + patients.getTotalPages());
//        System.out.println(patients.getTotalElements());
//        System.out.println(patients.getNumber());
//        List<Patient> content = patients.getContent();
//        Page<Patient> bySickness = patientRepository.findByIsSick(true, PageRequest.of(0, 5));
//        List<Patient> patientList =patientRepository.searchPatients("l%",200);
//        patientList.forEach(
//                p -> {
//                    System.out.println("======================================");
//                    System.out.println(p.getId());
//                    System.out.println(p.getName());
//                    System.out.println(p.getBirthday());
//                    System.out.println(p.getScore());
//                    System.out.println(p.getIsSick());
//                }
//        );
//        System.out.println("***************************");
//        Patient patient = patientRepository.findById(3L).orElse(null);
//        if (patient != null) {
//            System.out.println(patient.getName());
//            System.out.println(patient.getBirthday());
//            System.out.println(patient.getIsSick());
//        }
//        patient.setScore(8754);
//        patientRepository.save(patient);
//        //patientRepository.deleteById(1L);
//    }
//}
