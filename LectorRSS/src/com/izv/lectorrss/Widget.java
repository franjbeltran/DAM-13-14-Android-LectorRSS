package com.izv.lectorrss;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Widget extends AppWidgetProvider {
			
	@Override
	public void onUpdate(Context context, AppWidgetManager gestor,int[] appWidgetIds) {
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////ESTE CODIGO FUNCIONA PARA CREAR UN SERVICIO CON EL WIDGET///////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*//Se crea un objeto que tiene el widget en si
		ComponentName widget = new ComponentName(context,Widget.class);
		
		//Se obtiene un array con todos los widgets que tenemos
		int[] idWidgets = gestor.getAppWidgetIds(widget);
				
		//Se crea el intent que lanzara el servicio
		Intent intent = new Intent(context.getApplicationContext(),Servicio.class);
				
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,idWidgets);
				
		//Se inicia el servicio
		context.startService(intent);	*/
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////Esto resfresca manualmente el widget y lo hara segun/////////////////////////////////// 
////////////////////////////////////////el tiempo que se le de en la ultima linea///////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		final AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);  
		  
        final Calendar c = Calendar.getInstance();  
  
        final Intent intent = new Intent(context, Servicio.class);  
  
        PendingIntent pi=PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
        
        //En milisegundos cada cuanto queremos que se actualice
        am.setRepeating(AlarmManager.RTC, c.getTimeInMillis(), 900*1000, pi);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////FIN DE CODIGO PARA EL SERVICIO//////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////A PARTIR DE AQUI EL CODIGO SERIA////////////////////////////
		//////////////////////////////////////////////PARA HACER UN WIDGET NORMAL///////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*		
		//En este array (appWidgetIds) llega el ultimo widget que sacado, y cuando el sistema resfresque todos sus widgets llegaran todos al array
		for (int id : appWidgetIds) {
			
			//Forma 1
			/*RemoteViews vistasRemotas = new RemoteViews(	context.getPackageName(), R.layout.widget);
			
			//Al pulsar sobre el widget abrirá la actividad que pongamos aqui
			Intent intent = new Intent(context, MainActivity.class);
			
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,intent, 0);
			
			vistasRemotas.setOnClickPendingIntent(R.id.btElemento, pendingIntent);
			
			gestor.updateAppWidget(id, vistasRemotas);
			
			
			//Forma 2
			RemoteViews vistasRemotas = new RemoteViews(	context.getPackageName(), R.layout.widget);
			
			//vistasRemotas.setTextViewText(R.id.tvResultado, "Valor");
		
			Intent intent = new Intent(context, Widget.class);
			
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
			
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			vistasRemotas.setOnClickPendingIntent(R.id.btWidget, pendingIntent);
			
			gestor.updateAppWidget(id, vistasRemotas);
			
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////LA SIGUIENTE FORMA OBTIENE TODOS LOS WIDGETS QUE TENEMOS/////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//Para obtener todos los widgets que tenemos desplgados obtenemos un array con todos, se llama idWidgets
		/*ComponentName widget = new ComponentName(context,Widget.class);
		
		int[] idWidgets = gestor.getAppWidgetIds(widget);
		
		for (int id : idWidgets) {

			//AQUI VA TODO EL CODIGO QUE TENEMOS EN LA FORMA DE ARRIBA
			
		}*/

	}
	
	//Este metodo se ejecuta cuando no quede ningún widget en el escritorio
	@Override  
	public void onDisabled(Context context){
				
		Toast.makeText(context, context.getResources().getString(R.string.sin_widgets), Toast.LENGTH_SHORT).show();
		
		//Se crea el mismo alarmManager con el mismo intent que se ha creado arriba
		final AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);    
		final Intent intent = new Intent(context, Servicio.class);  
		final PendingIntent pi= PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
		
		//Cuando el pendingIntent este creado se cancela el alarmManager
        am.cancel(pi);  
        
    }
	
}