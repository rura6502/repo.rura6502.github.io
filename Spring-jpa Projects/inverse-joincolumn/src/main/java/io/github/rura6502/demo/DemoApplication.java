package io.github.rura6502.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired CctvRepository cctvRepo;
  @Autowired ViewRepository viewRepo;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {
      Cctv cctv1 = new Cctv(null, "cctv01");
      Cctv cctv2 = new Cctv(null, "cctv02");
      Cctv cctv3 = new Cctv(null, "cctv03");
      // Cctv cctv4 = new Cctv(null, "cctv04");
      // Cctv cctv5 = new Cctv(null, "cctv05");

      List<Cctv> cctvs = cctvRepo.saveAll(List.of(cctv1, cctv2, cctv3, cctv4, cctv5));

      View view = new View(null, "view01", cctvs);
      viewRepo.save(view);

      System.out.println(viewRepo.getCctvsByViewId(view.getId()));
    };
  }

}

interface ViewRepository extends JpaRepository<View, Long> {
  @Query("select distinct v.cctvs from View v left join v.cctvs c where v.id=:viewId")
  List<Cctv> getCctvsByViewId(@Param("viewId") long viewId);
}
interface CctvRepository extends JpaRepository<Cctv, Long> {}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class View {
  @Id @GeneratedValue private Long id;
  private String name;
  @OneToMany
  @JoinTable(name = "view_cctvs", joinColumns = @JoinColumn(name = "view_id"), inverseJoinColumns = @JoinColumn(name = "cctv_id"))
  private List<Cctv> cctvs;
}


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Cctv {
  @Id @GeneratedValue private Long id;
  private String name;
}