package messages.dao;

import java.util.List;

import messages.objects.Comments;

public interface Dao {

	public void create(Comments comments);

	public Comments select(int id);

	public List<Comments> selectAll();

}
