package com.mekni.bactechai;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    // ============================================================
    // COLORS
    // ============================================================

    private final int BG = Color.rgb(10, 14, 22);
    private final int SURFACE = Color.rgb(18, 24, 34);
    private final int SURFACE_2 = Color.rgb(25, 32, 44);
    private final int PRIMARY = Color.rgb(70, 125, 255);
    private final int PRIMARY_DARK = Color.rgb(45, 91, 210);
    private final int TEXT = Color.rgb(242, 245, 250);
    private final int MUTED = Color.rgb(150, 160, 175);
    private final int SUCCESS = Color.rgb(60, 190, 125);
    private final int WARNING = Color.rgb(240, 175, 70);

    private LinearLayout mainContainer;
    private LinearLayout contentContainer;

    private int currentPage = 0;

    // ============================================================
    // DP
    // ============================================================

    private int dp(int value) {
        return Math.round(
                value * getResources().getDisplayMetrics().density
        );
    }

    // ============================================================
    // CREATE
    // ============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);

        buildApplication();
    }

    // ============================================================
    // MAIN APPLICATION
    // ============================================================

    private void buildApplication() {

        mainContainer = new LinearLayout(this);
        mainContainer.setOrientation(LinearLayout.VERTICAL);
        mainContainer.setBackgroundColor(BG);

        // Content
        contentContainer = new LinearLayout(this);
        contentContainer.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);

        scrollView.addView(
                contentContainer,
                new ScrollView.LayoutParams(
                        -1,
                        -1
                )
        );

        mainContainer.addView(
                scrollView,
                new LinearLayout.LayoutParams(
                        -1,
                        0,
                        1
                )
        );

        // Bottom navigation
        LinearLayout bottomNavigation = createBottomNavigation();

        mainContainer.addView(
                bottomNavigation,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(76)
                )
        );

        setContentView(mainContainer);

        showHome();
    }

    // ============================================================
    // BOTTOM NAVIGATION
    // ============================================================

    private LinearLayout createBottomNavigation() {

        LinearLayout nav = new LinearLayout(this);

        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(
                dp(8),
                dp(6),
                dp(8),
                dp(6)
        );

        nav.setBackgroundColor(SURFACE);

        addNavigationButton(
                nav,
                "⌂",
                "الرئيسية",
                0
        );

        addNavigationButton(
                nav,
                "🤖",
                "AI",
                1
        );

        addNavigationButton(
                nav,
                "📚",
                "المواد",
                2
        );

        addNavigationButton(
                nav,
                "📝",
                "تمارين",
                3
        );

        addNavigationButton(
                nav,
                "⚙",
                "الإعدادات",
                4
        );

        return nav;
    }

    private void addNavigationButton(
            LinearLayout parent,
            String icon,
            String text,
            int page
    ) {

        LinearLayout item = new LinearLayout(this);

        item.setOrientation(LinearLayout.VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setPadding(
                dp(3),
                dp(3),
                dp(3),
                dp(3)
        );

        TextView iconView = new TextView(this);
        iconView.setText(icon);
        iconView.setTextSize(24);
        iconView.setGravity(Gravity.CENTER);

        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(10);
        textView.setGravity(Gravity.CENTER);

        item.addView(
                iconView,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(35)
                )
        );

        item.addView(
                textView,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(22)
                )
        );

        item.setOnClickListener(v -> {

            currentPage = page;

            switch (page) {

                case 0:
                    showHome();
                    break;

                case 1:
                    showAI();
                    break;

                case 2:
                    showSubjects();
                    break;

                case 3:
                    showExercises();
                    break;

                case 4:
                    showSettings();
                    break;
            }
        });

        parent.addView(
                item,
                new LinearLayout.LayoutParams(
                        0,
                        -1,
                        1
                )
        );
    }

    // ============================================================
    // COMMON
    // ============================================================

    private void clearContent() {

        contentContainer.removeAllViews();

        contentContainer.setPadding(
                dp(18),
                dp(20),
                dp(18),
                dp(30)
        );
    }

    private TextView title(String text) {

        TextView t = new TextView(this);

        t.setText(text);
        t.setTextColor(TEXT);
        t.setTextSize(27);
        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        t.setGravity(Gravity.RIGHT);

        return t;
    }

    private TextView subtitle(String text) {

        TextView t = new TextView(this);

        t.setText(text);
        t.setTextColor(MUTED);
        t.setTextSize(14);
        t.setGravity(Gravity.RIGHT);

        return t;
    }

    private TextView sectionTitle(String text) {

        TextView t = new TextView(this);

        t.setText(text);
        t.setTextColor(TEXT);
        t.setTextSize(19);
        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        t.setGravity(Gravity.RIGHT);

        t.setPadding(
                0,
                dp(18),
                0,
                dp(10)
        );

        return t;
    }

    private GradientDrawable background(
            int color,
            int radius
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));

        return drawable;
    }

    private LinearLayout card() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(18)
        );

        card.setBackground(
                background(SURFACE, 18)
        );

        return card;
    }

    private Button actionButton(
            String text
    ) {

        Button button =
                new Button(this);

        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setTextSize(15);
        button.setAllCaps(false);

        button.setBackground(
                background(PRIMARY, 14)
        );

        return button;
    }

    // ============================================================
    // HOME
    // ============================================================

    private void showHome() {

        clearContent();

        LinearLayout header =
                new LinearLayout(this);

        header.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView logo =
                new TextView(this);

        logo.setText("🎓");
        logo.setTextSize(38);

        header.addView(
                logo,
                new LinearLayout.LayoutParams(
                        dp(50),
                        dp(60)
                )
        );

        LinearLayout headerText =
                new LinearLayout(this);

        headerText.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView appName =
                new TextView(this);

        appName.setText("BacTech AI");
        appName.setTextColor(TEXT);
        appName.setTextSize(23);
        appName.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView welcome =
                new TextView(this);

        welcome.setText(
                "مساعدك الذكي للنجاح في البكالوريا"
        );

        welcome.setTextColor(MUTED);
        welcome.setTextSize(12);

        headerText.addView(appName);
        headerText.addView(welcome);

        header.addView(
                headerText,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        contentContainer.addView(header);

        // AI CARD

        LinearLayout aiCard = card();

        aiCard.setPadding(
                dp(20),
                dp(20),
                dp(20),
                dp(20)
        );

        TextView aiLabel =
                new TextView(this);

        aiLabel.setText("🤖  AI TUTOR");
        aiLabel.setTextColor(PRIMARY);
        aiLabel.setTextSize(13);
        aiLabel.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        aiCard.addView(aiLabel);

        TextView aiTitle =
                new TextView(this);

        aiTitle.setText(
                "اسألني عن أي شيء في دراستك"
        );

        aiTitle.setTextColor(TEXT);
        aiTitle.setTextSize(22);
        aiTitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        aiTitle.setPadding(
                0,
                dp(8),
                0,
                dp(5)
        );

        aiCard.addView(aiTitle);

        TextView aiDescription =
                new TextView(this);

        aiDescription.setText(
                "شرح • حل تمارين • مراجعة • تحليل الصور"
        );

        aiDescription.setTextColor(MUTED);
        aiDescription.setTextSize(13);

        aiCard.addView(aiDescription);

        Button askAI =
                actionButton("ابدأ المحادثة  →");

        LinearLayout.LayoutParams aiButtonParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(52)
                );

        aiButtonParams.topMargin =
                dp(15);

        aiCard.addView(
                askAI,
                aiButtonParams
        );

        askAI.setOnClickListener(
                v -> showAI()
        );

        contentContainer.addView(
                aiCard,
                marginParams(0, 20, 0, 0)
        );

        // PROGRESS

        contentContainer.addView(
                sectionTitle("تقدمك اليوم")
        );

        LinearLayout progressCard = card();

        TextView progressText =
                new TextView(this);

        progressText.setText(
                "🎯  هدف اليوم\n\n" +
                "أكملت 0% من جلسة المراجعة"
        );

        progressText.setTextColor(TEXT);
        progressText.setTextSize(15);

        progressCard.addView(progressText);

        ProgressBar progress =
                new ProgressBar(
                        this,
                        null,
                        android.R.attr.progressBarStyleHorizontal
                );

        progress.setMax(100);
        progress.setProgress(0);

        LinearLayout.LayoutParams progressParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(8)
                );

        progressParams.topMargin =
                dp(15);

        progressCard.addView(
                progress,
                progressParams
        );

        contentContainer.addView(
                progressCard
        );

        // SUBJECTS

        contentContainer.addView(
                sectionTitle("المواد الدراسية")
        );

        addSubjectCard(
                "📐",
                "الرياضيات",
                "دوال • أعداد • هندسة • احتمالات",
                PRIMARY
        );

        addSubjectCard(
                "⚡",
                "الفيزياء",
                "كهرباء • ميكانيك • طاقة",
                WARNING
        );

        addSubjectCard(
                "🧪",
                "الكيمياء",
                "تفاعلات • حسابات • محاليل",
                SUCCESS
        );

        addSubjectCard(
                "⚙️",
                "التقنية",
                "أنظمة • كهرباء • إلكترونيات",
                Color.rgb(180, 100, 240)
        );

        // QUICK ACTIONS

        contentContainer.addView(
                sectionTitle("اختصارات")
        );

        LinearLayout quick =
                new LinearLayout(this);

        quick.setOrientation(
                LinearLayout.HORIZONTAL
        );

        addQuickButton(
                quick,
                "📝",
                "تمرين",
                0
        );

        addQuickButton(
                quick,
                "⏱️",
                "مراجعة",
                1
        );

        addQuickButton(
                quick,
                "📊",
                "التقدم",
                2
        );

        contentContainer.addView(quick);
    }

    private void addSubjectCard(
            String icon,
            String name,
            String description,
            int accent
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.HORIZONTAL
        );

        card.setGravity(
                Gravity.CENTER_VERTICAL
        );

        card.setPadding(
                dp(15),
                dp(13),
                dp(15),
                dp(13)
        );

        card.setBackground(
                background(SURFACE, 15)
        );

        TextView iconView =
                new TextView(this);

        iconView.setText(icon);
        iconView.setTextSize(28);
        iconView.setGravity(Gravity.CENTER);

        card.addView(
                iconView,
                new LinearLayout.LayoutParams(
                        dp(50),
                        dp(55)
                )
        );

        LinearLayout info =
                new LinearLayout(this);

        info.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView nameView =
                new TextView(this);

        nameView.setText(name);
        nameView.setTextColor(TEXT);
        nameView.setTextSize(17);
        nameView.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView desc =
                new TextView(this);

        desc.setText(description);
        desc.setTextColor(MUTED);
        desc.setTextSize(11);

        info.addView(nameView);
        info.addView(desc);

        card.addView(
                info,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        TextView arrow =
                new TextView(this);

        arrow.setText("‹");
        arrow.setTextColor(accent);
        arrow.setTextSize(28);

        card.addView(
                arrow,
                new LinearLayout.LayoutParams(
                        dp(35),
                        dp(50)
                )
        );

        contentContainer.addView(
                card,
                marginParams(0, 5, 0, 5)
        );
    }

    private void addQuickButton(
            LinearLayout parent,
            String icon,
            String text,
            int type
    ) {

        LinearLayout button =
                new LinearLayout(this);

        button.setOrientation(
                LinearLayout.VERTICAL
        );

        button.setGravity(Gravity.CENTER);

        button.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(8)
        );

        button.setBackground(
                background(SURFACE, 15)
        );

        TextView i =
                new TextView(this);

        i.setText(icon);
        i.setTextSize(25);
        i.setGravity(Gravity.CENTER);

        TextView t =
                new TextView(this);

        t.setText(text);
        t.setTextColor(TEXT);
        t.setTextSize(11);
        t.setGravity(Gravity.CENTER);

        button.addView(i);
        button.addView(t);

        button.setOnClickListener(v -> {

            if (type == 0) {
                showExercises();
            } else if (type == 2) {
                showProgress();
            }
        });

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        0,
                        dp(90),
                        1
                );

        p.setMargins(
                dp(4),
                dp(4),
                dp(4),
                dp(4)
        );

        parent.addView(button, p);
    }

    // ============================================================
    // AI
    // ============================================================

    private void showAI() {

        clearContent();

        TextView t =
                title("🤖 المساعد الذكي");

        contentContainer.addView(t);

        TextView sub =
                subtitle(
                        "اسأل، أرسل تمرينًا، أو اطلب شرحًا"
                );

        sub.setPadding(
                0,
                dp(5),
                0,
                dp(18)
        );

        contentContainer.addView(sub);

        LinearLayout chat =
                new LinearLayout(this);

        chat.setOrientation(
                LinearLayout.VERTICAL
        );

        chat.setPadding(
                dp(15),
                dp(15),
                dp(15),
                dp(15)
        );

        chat.setBackground(
                background(SURFACE, 18)
        );

        TextView welcome =
                new TextView(this);

        welcome.setText(
                "👋 مرحبًا!\n\n" +
                "أنا BacTech AI.\n" +
                "يمكنني مساعدتك في فهم الدروس، " +
                "حل التمارين، ومراجعة إجاباتك."
        );

        welcome.setTextColor(TEXT);
        welcome.setTextSize(15);
        welcome.setGravity(Gravity.RIGHT);

        chat.addView(
   