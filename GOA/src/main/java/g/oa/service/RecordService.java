package g.oa.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import g.oa.bean.Record;
import g.oa.util.MyBatisUtil;


public class RecordService {
	
	public void initDBTable() {
		SqlSession session=null;;
		Record r = new Record();
		r.setType("~");
		try {
			session = MyBatisUtil.getSqlSession();
			session.selectList("mapper.record.select",r);
			session.close();
		} catch (Exception e) {
			if(e.getCause().getMessage().equals("Table/View 'RECORD' does not exist.")) {
				if(session==null)
					session = MyBatisUtil.getSqlSession();
				session.update("mapper.record.create");
				session.commit();
			}
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	public void save(Record record) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("mapper.record.insert",record);
		session.close();
	}
	
	public void delete(Record record) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.delete("mapper.record.delete",record);
		session.close();
	}
	public void update(Record record) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.update("mapper.record.update",record);
		session.close();
	}
	public List<Record> search(Record record){
		SqlSession session = MyBatisUtil.getSqlSession();
		List<Record> list = session.selectList("mapper.record.select",record);
		session.close();
		return list;
	}
}
