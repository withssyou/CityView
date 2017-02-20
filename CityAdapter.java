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
	
	//保存第一次出现的索引值
	private StringBuffer buffer = new StringBuffer();
	//保存第一次出现索引值得城市名字 // 集合里面存储的全部是需要显示完整布局的城市   26
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
		
		//赋值
		City city = getItem(position);
		
		String key = city.getSortKey();
		String name = city.getName();
		
		//判断key是否是第一次出现
		if(buffer.indexOf(key)== -1){
			buffer.append(key);
			//保存第一次出现位置的城市
			citys.add(name);
		}
		
		//判断即将加载的城市，是否是集合里面记录过的城市
		if(citys.contains(name)){
			holder.sortKey.setVisibility(View.VISIBLE);
			holder.sortKey.setText(key);
		}else{
			holder.sortKey.setVisibility(View.GONE);
		}
		
		//显示城市信息
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
