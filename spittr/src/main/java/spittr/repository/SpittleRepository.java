package spittr.repository;

import java.util.List;
import spittr.domain.Spittle;



public interface SpittleRepository {
	
	long count(); //��ѯ���ӵ�����

	List<Spittle> findRecent();

	List<Spittle> findRecent(int count);

	Spittle findOne(long id);

	List<Spittle> findBySpitterId(long spitterId);

	Spittle save(Spittle spittle);

	void delete(long id);
	

}
