package org.weewelchie.qr.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeDataDAO extends JpaRepository<QRCodeData,Long> {

   QRCodeData findByContent(String content);

   @Query("SELECT q FROM QRCodeData q WHERE q.content = :content")
   QRCodeData retrieveByContent(@Param("content") String content) ;
}
