package tw.brad.spring4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring4.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
