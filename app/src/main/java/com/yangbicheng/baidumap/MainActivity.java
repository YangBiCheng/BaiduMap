package com.yangbicheng.baidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);
        mapView.setLogoPosition(LogoPosition.logoPostionCenterTop);//logo位置

        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//地图显示方式
        baiduMap.setTrafficEnabled(true);//实时交通图
        baiduMap.setBaiduHeatMapEnabled(true);//城市热力图
//        baiduMap.setPadding(100, 100, 100, 100);//设置地图边界区域
        float maxZoomLevel = baiduMap.getMaxZoomLevel();
        float minZoomLevel = baiduMap.getMinZoomLevel();
        Log.e(TAG, "minZoomLevel=" + minZoomLevel + ",maxZoomLevel=" + maxZoomLevel);//E/MainActivity: minZoomLevel=3.0,maxZoomLevel=20.0
//        baiduMap.setMaxAndMinZoomLevel(20L,3L);//设置比例尺

        //给指定的地方添加图标
        LatLng point = new LatLng(39.963175, 116.400244);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        ArrayList<BitmapDescriptor> giflist = new ArrayList<>();
        BitmapDescriptor bitmapB = BitmapDescriptorFactory.fromResource(R.drawable.icon_markb);
        BitmapDescriptor bitmapC = BitmapDescriptorFactory.fromResource(R.drawable.icon_markc);
        BitmapDescriptor bitmapD = BitmapDescriptorFactory.fromResource(R.drawable.icon_markd);
        BitmapDescriptor bitmapE = BitmapDescriptorFactory.fromResource(R.drawable.icon_marke);
        BitmapDescriptor bitmapF = BitmapDescriptorFactory.fromResource(R.drawable.icon_markf);
        giflist.add(bitmapB);
        giflist.add(bitmapC);
        giflist.add(bitmapD);
        giflist.add(bitmapE);
        giflist.add(bitmapF);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(point)//设置mark的位置
//                .icon(bitmap)//设置mark图标
                .icons(giflist)//设置多个图标轮播
                .zIndex(9)//设置mark所在图层
                .draggable(true)//设置手势拖拽
                .period(100)//轮播时间
                .alpha(0.5f);//透明度
        markerOptions.animateType(MarkerOptions.MarkerAnimateType.drop);//动画模式:从地上生长和从天上落下
        Overlay overlay = baiduMap.addOverlay(markerOptions);//添加mark
//        overlay.remove();

        //marker拖拽的监听
        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            //拖拽中
            @Override
            public void onMarkerDrag(Marker marker) {
                Log.e(TAG, "onMarkerDrag latitude:" + marker.getPosition().latitude + "longitude:" + marker.getPosition().longitude);
            }

            //拖拽结束
            @Override
            public void onMarkerDragEnd(Marker marker) {
                Log.e(TAG, "onMarkerDragEnd latitude:" + marker.getPosition().latitude + "longitude:" + marker.getPosition().longitude);
            }

            //开始拖拽
            @Override
            public void onMarkerDragStart(Marker marker) {
                Log.e(TAG, "onMarkerDragStart latitude:" + marker.getPosition().latitude + "longitude:" + marker.getPosition().longitude);
            }
        });

        baiduMap.showMapPoi(false);//将底图标注设置为隐藏

        LatLng pt1 = new LatLng(39.93923, 116.357428);
        LatLng pt2 = new LatLng(39.91923, 116.327428);
        LatLng pt3 = new LatLng(39.89923, 116.347428);
        LatLng pt4 = new LatLng(39.89923, 116.367428);
        LatLng pt5 = new LatLng(39.91923, 116.387428);
        List<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);
        pts.add(pt3);
        pts.add(pt4);
        pts.add(pt5);
        //构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Stroke(5, 0xAA00FF00))
                .fillColor(0xAAFFFF00);
        //在地图上添加多边形Option，用于显示
        baiduMap.addOverlay(polygonOption);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
