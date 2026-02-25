package com.hellocuriosity.drappula

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.hellocuriosity.drappula.consent.ConsentManager
import com.hellocuriosity.drappula.data.network.HttpEngineFactory
import com.hellocuriosity.drappula.data.network.NetworkModule
import com.hellocuriosity.drappula.data.network.converters.FeedbackConverter
import com.hellocuriosity.drappula.data.repository.SlackCloud
import com.hellocuriosity.drappula.data.repository.SlackRepository
import com.hellocuriosity.drappula.providers.PreferenceProvider
import com.hellocuriosity.drappula.reporting.ReportHandler

class ApplicationComponent(
    val applicationContext: Context,
) {
    val dispatchers: CoroutineDispatchers by lazy { CoroutineDispatchers.default }
    val consentManager: ConsentManager by lazy { ConsentManager(applicationContext) }
    val preferenceProvider: PreferenceProvider by lazy { PreferenceProvider(applicationContext) }
    val reportHandler: ReportHandler by lazy {
        ReportHandler(
            analytics = FirebaseAnalytics.getInstance(applicationContext),
            crashlytics = FirebaseCrashlytics.getInstance(),
        )
    }

    val slackRepository: SlackRepository by lazy {
        SlackRepository(
            cloud =
                SlackCloud(
                    service = networkModule.service,
                    feedbackConverter =
                        FeedbackConverter(
                            channelId = BuildConfig.SLACK_CHANNEL_ID,
                            platform = "Android",
                        ),
                ),
        )
    }

    private val networkModule: NetworkModule by lazy {
        NetworkModule(factory = HttpEngineFactory(), token = BuildConfig.SLACK_BOT_TOKEN)
    }
}
