package projet.yankiba;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessagesAdapter extends BaseAdapter{

	private List<MessageDB> listMess;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public MessagesAdapter (Context context, List<MessageDB> listM){
		mContext = context;
		listMess = listM;
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return listMess.size();
	}

	@Override
	public Object getItem(int position) {
		return listMess.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		if(convertView == null){
			//layoutItem = (LinearLayout) 
			vi = mInflater.inflate(R.layout.liste_message, null);	
		}
		TextView tvMess = (TextView)vi.findViewById(R.id.tVMess);
		
		MessageDB mess = listMess.get(position);
		tvMess.setText(mess.getContenu());
		
		return vi;
	}
	
	public void add(MessageDB item){
		listMess.add(item);
	}

	public void clear() {
		listMess.clear();
	}

}
