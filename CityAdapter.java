package com.adapter;

import java.util.ArrayList;
import java.util.List;

import com.entity.City;
import com.example.dianping.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CityAdapter extends BaseViewAdapter<City>{
	
	public CityAdapter(List<City> datas){
		super(datas);
	}
	
	//�����һ�γ��ֵ�����ֵ
	private StringBuffer buffer = new StringBuffer();
	//�����һ�γ�������ֵ�ó������� // ��������洢��ȫ������Ҫ��ʾ�������ֵĳ���   26
	private List<String> citys = new ArrayList<String>();

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CityHolder holder;
		
		if(convertView==null){
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_city_item, null);
			holder = new CityHolder();
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);
		}else{
			holder = (CityHolder) convertView.getTag();
		}
		
		//��ֵ
		City city = getItem(position);
		
		String key = city.getSortKey();
		String name = city.getName();
		
		//�ж�key�Ƿ��ǵ�һ�γ���
		if(buffer.indexOf(key)== -1){
			buffer.append(key);
			//�����һ�γ���λ�õĳ���
			citys.add(name);
		}
		
		//�жϼ������صĳ��У��Ƿ��Ǽ��������¼���ĳ���
		if(citys.contains(name)){
			holder.sortKey.setVisibility(View.VISIBLE);
			holder.sortKey.setText(key);
		}else{
			holder.sortKey.setVisibility(View.GONE);
		}
		
		//��ʾ������Ϣ
		holder.cityName.setText(name);
		
		return convertView;
	}

	public class CityHolder{
		@ViewInject(R.id.home_city_item_sortkey)
		public TextView sortKey;
		@ViewInject(R.id.home_city_item_cityname)
		public TextView cityName;
	}

}
