package br.com.marcos.eitacasei.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import br.com.marcos.eitacasei.activities.ListaPresentesActivity;
import br.com.marcos.eitacasei.activities.LoginActivity;

/**
 * Responsável por notificar qual casal está logado na aplicação
 * Created by Marcos on 28/08/18.
 */
public class SessaoService extends Service {

    private static final int NOTIFICACAO_CASAL_LOGADO = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Intent intentNotificacao = new Intent(this, ListaPresentesActivity.class);
        PendingIntent intentPendente =
                PendingIntent.getActivity(this, 0, intentNotificacao, 0);

        SharedPreferences preferencias = getSharedPreferences("PREF_LOGIN", MODE_PRIVATE);

        Notification notificacao = new Notification.Builder(this)
                .setContentTitle("EitaCasei")
                .setContentText("Casal " + preferencias.getString("login", "") + " está logado")
                .setContentIntent(intentPendente)
                .setTicker("Serviço de Notificação de Casal Logado")
                .build();

        startForeground(NOTIFICACAO_CASAL_LOGADO, notificacao);

        return START_STICKY;
    }
}
