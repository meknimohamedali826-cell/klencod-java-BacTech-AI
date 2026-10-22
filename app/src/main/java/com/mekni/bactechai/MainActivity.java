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

    private ArrayList<String> chatMessages =
            new ArrayList<>();

    // ============================================================
    // DP
    // ============================================================

    private int dp(int value) {
        return Math.round(
                value * getResources()
                        .getDisplayMetrics()
                        .density
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

        mainContainer =
                new LinearLayout(this);

        mainContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        mainContainer.setBackgroundColor(BG);

        contentContainer =
                new LinearLayout(this);

        contentContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        ScrollView scrollView =
                new ScrollView(this);

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

        LinearLayout bottomNavigation =
                createBottomNavigation();

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

        LinearLayout nav =
                new LinearLayout(this);

        nav.setOrientation(
                LinearLayout.HORIZONTAL
        );

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

        LinearLayout item =
                new LinearLayout(this);

        item.setOrientation(
                LinearLayout.VERTICAL
        );

        item.setGravity(Gravity.CENTER);

        item.setPadding(
                dp(3),
                dp(3),
                dp(3),
                dp(3)
        );

        TextView iconView =
                new TextView(this);

        iconView.setText(icon);
        iconView.setTextSize(24);
        iconView.setGravity(Gravity.CENTER);

        TextView textView =
                new TextView(this);

        textView.setText(text);
        textView.setTextColor(TEXT);
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

        item.setOnClickListener(
                v -> {

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
                }
        );

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

        TextView t =
                new TextView(this);

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

        TextView t =
                new TextView(this);

        t.setText(text);
        t.setTextColor(MUTED);
        t.setTextSize(14);

        t.setGravity(Gravity.RIGHT);

        return t;
    }

    private TextView sectionTitle(
            String text
    ) {

        TextView t =
                new TextView(this);

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
        drawable.setCornerRadius(
                dp(radius)
        );

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

    private LinearLayout.LayoutParams marginParams(
            int left,
            int top,
            int right,
            int bottom
    ) {

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        p.setMargins(
                dp(left),
                dp(top),
                dp(right),
                dp(bottom)
        );

        return p;
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
        logo.setGravity(Gravity.CENTER);

        header.addView(
                logo,
                new LinearLayout.LayoutParams(
                        dp(55),
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

        // ========================================================
        // AI CARD
        // ========================================================

        LinearLayout aiCard = card();

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
                "شرح • حل تمارين • مراجعة • مساعد دراسي"
        );

        aiDescription.setTextColor(MUTED);
        aiDescription.setTextSize(13);

        aiCard.addView(aiDescription);

        Button askAI =
                actionButton(
                        "ابدأ المحادثة  →"
                );

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
                marginParams(
                        0,
                        20,
                        0,
                        0
                )
        );

        // ========================================================
        // PROGRESS
        // ========================================================

        contentContainer.addView(
                sectionTitle("تقدمك اليوم")
        );

        LinearLayout progressCard =
                card();

        TextView progressText =
                new TextView(this);

        progressText.setText(
                "🎯  هدف اليوم\n\n" +
                "ابدأ أول جلسة مراجعة لك"
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

        // ========================================================
        // SUBJECTS
        // ========================================================

        contentContainer.addView(
                sectionTitle(
                        "المواد الدراسية"
                )
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

        // ========================================================
        // QUICK ACTIONS
        // ========================================================

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

    // ============================================================
    // SUBJECT CARD
    // ============================================================

    private void addSubjectCard(
            String icon,
            String name,
            String description,
            int accent
    ) {

        LinearLayout subjectCard =
                new LinearLayout(this);

        subjectCard.setOrientation(
                LinearLayout.HORIZONTAL
        );

        subjectCard.setGravity(
                Gravity.CENTER_VERTICAL
        );

        subjectCard.setPadding(
                dp(15),
                dp(13),
                dp(15),
                dp(13)
        );

        subjectCard.setBackground(
                background(SURFACE, 15)
        );

        TextView iconView =
                new TextView(this);

        iconView.setText(icon);
        iconView.setTextSize(28);
        iconView.setGravity(
                Gravity.CENTER
        );

        subjectCard.addView(
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

        subjectCard.addView(
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

        subjectCard.addView(
                arrow,
                new LinearLayout.LayoutParams(
                        dp(35),
                        dp(50)
                )
        );

        contentContainer.addView(
                subjectCard,
                marginParams(
                        0,
                        5,
                        0,
                        5
                )
        );
    }

    // ============================================================
    // QUICK BUTTON
    // ============================================================

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

        button.setGravity(
                Gravity.CENTER
        );

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

        button.setOnClickListener(
                v -> {

                    if (type == 0) {
                        showExercises();
                    } else if (type == 1) {
                        showAI();
                    } else if (type == 2) {
                        showProgress();
                    }
                }
        );

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
    // AI PAGE
    // ============================================================

    private void showAI() {

        clearContent();

        contentContainer.addView(
                title("🤖 المساعد الذكي")
        );

        TextView sub =
                subtitle(
                        "اسأل، اشرح، راجع، أو اطلب حل تمرين"
                );

        sub.setPadding(
                0,
                dp(5),
                0,
                dp(15)
        );

        contentContainer.addView(sub);

        // ========================================================
        // CHAT AREA
        // ========================================================

        LinearLayout chatBox =
                new LinearLayout(this);

        chatBox.setOrientation(
                LinearLayout.VERTICAL
        );

        chatBox.setPadding(
                dp(12),
                dp(12),
                dp(12),
                dp(12)
        );

        chatBox.setBackground(
                background(SURFACE, 18)
        );

        TextView welcome =
                new TextView(this);

        welcome.setText(
                "👋 مرحبًا بك في BacTech AI\n\n" +
                "أنا مساعدك الدراسي.\n" +
                "يمكنني مساعدتك في:\n\n" +
                "• الرياضيات\n" +
                "• الفيزياء\n" +
                "• الكيمياء\n" +
                "• التقنية\n" +
                "• مراجعة البكالوريا"
        );

        welcome.setTextColor(TEXT);
        welcome.setTextSize(15);
        welcome.setGravity(Gravity.RIGHT);

        welcome.setPadding(
                dp(12),
                dp(12),
                dp(12),
                dp(12)
        );

        chatBox.addView(welcome);

        if (!chatMessages.isEmpty()) {

            for (String message :
                    chatMessages) {

                TextView msg =
                        new TextView(this);

                msg.setText(message);
                msg.setTextColor(TEXT);
                msg.setTextSize(14);
                msg.setGravity(Gravity.RIGHT);

                msg.setPadding(
                        dp(12),
                        dp(12),
                        dp(12),
                        dp(12)
                );

                LinearLayout.LayoutParams mp =
                        marginParams(
                                0,
                                8,
                                0,
                                0
                        );

                chatBox.addView(
                        msg,
                        mp
                );
            }
        }

        contentContainer.addView(
                chatBox,
                marginParams(
                        0,
                        5,
                        0,
                        15
                )
        );

        // ========================================================
        // INPUT
        // ========================================================

        LinearLayout inputRow =
                new LinearLayout(this);

        inputRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        EditText input =
                new EditText(this);

        input.setHint(
                "اكتب سؤالك هنا..."
        );

        input.setHintTextColor(MUTED);
        input.setTextColor(TEXT);
        input.setTextSize(14);

        input.setGravity(
                Gravity.RIGHT |
                Gravity.CENTER_VERTICAL
        );

        input.setSingleLine(false);

        input.setMinHeight(
                dp(52)
        );

        input.setPadding(
                dp(14),
                dp(8),
                dp(14),
                dp(8)
        );

        input.setBackground(
                background(SURFACE_2, 15)
        );

        inputRow.addView(
                input,
                new LinearLayout.LayoutParams(
                        0,
                        dp(58),
                        1
                )
        );

        Button send =
                actionButton("إرسال");

        LinearLayout.LayoutParams sendParams =
                new LinearLayout.LayoutParams(
                        dp(90),
                        dp(58)
                );

        sendParams.leftMargin =
                dp(8);

        inputRow.addView(
                send,
                sendParams
        );

        contentContainer.addView(inputRow);

        // ========================================================
        // SEND
        // ========================================================

        send.setOnClickListener(
                v -> {

                    String question =
                            input.getText()
                                    .toString()
                                    .trim();

                    if (question.isEmpty()) {
                        return;
                    }

                    chatMessages.add(
                            "👤 أنت:\n" +
                            question
                    );

                    String answer =
                            generateLocalAnswer(
                                    question
                            );

                    chatMessages.add(
                            "🤖 BacTech AI:\n" +
                            answer
                    );

                    input.setText("");

                    showAI();
                }
        );

        // ========================================================
        // SUGGESTIONS
        // ========================================================

        contentContainer.addView(
                sectionTitle(
                        "أسئلة سريعة"
                )
        );

        addSuggestion(
                "اشرح لي درس RC",
                "اشرح لي درس RC بطريقة سهلة"
        );

        addSuggestion(
                "ساعدني في الرياضيات",
                "أريد مساعدة في الرياضيات"
        );

        addSuggestion(
                "مراجعة الفيزياء",
                "أريد مراجعة فيزياء للبكالوريا"
        );

        addSuggestion(
                "كيف أراجع التقنية؟",
                "أعطني طريقة لمراجعة التقنية"
        );
    }

    // ============================================================
    // AI LOCAL ANSWER
    // ============================================================

    private String generateLocalAnswer(
            String question
    ) {

        String q =
                question.toLowerCase();

        if (q.contains("rc")) {

            return
                    "دائرة RC تتكون عادةً من مقاومة R ومكثف C.\n\n" +
                    "عند الشحن، يرتفع جهد المكثف Uc تدريجيًا، " +
                    "بينما يتناقص التيار مع الزمن.\n\n" +
                    "إذا أرسلت لي التمرين أو صورة منه، " +
                    "يمكنني شرح المعطيات والخطوات.";
        }

        if (q.contains("رياضيات") ||
                q.contains("math")) {

            return
                    "أكيد 👍\n\n" +
                    "أرسل لي التمرين كما هو، " +
                    "وسأساعدك في فهم المطلوب ثم حلّه خطوة بخطوة.";
        }

        if (q.contains("فيزياء") ||
                q.contains("physique")) {

            return
                    "يمكنني مساعدتك في الفيزياء، " +
                    "خصوصًا الكهرباء والميكانيك والطاقة.\n\n" +
                    "أرسل التمرين أو اكتب الجزء الذي لم تفهمه.";
        }

        if (q.contains("كيمياء") ||
                q.contains("chimie")) {

            return
                    "في الكيمياء يمكنني مساعدتك في:\n" +
                    "• جدول التقدم\n" +
                    "• كمية المادة\n" +
                    "• المتفاعل المحد\n" +
                    "• المحاليل\n\n" +
                    "أرسل التمرين وسنحلّه معًا.";
        }

        if (q.contains("تقنية") ||
                q.contains("electrique")) {

            return
                    "التقنية تشمل عدة مواضيع مثل الأنظمة، " +
                    "الكهرباء والإلكترونيات.\n\n" +
                    "اكتب اسم الدرس أو أرسل صورة التمرين.";
        }

        if (q.contains("مرحبا") ||
                q.contains("سلام") ||
                q.contains("hello")) {

            return
                    "أهلًا بك 👋\n" +
                    "أنا BacTech AI، جاهز لمساعدتك في دراستك.";
        }

        return
                "فهمت سؤالك 👍\n\n" +
                "أنا حاليًا في وضع المساعد المحلي، " +
                "لذلك أستطيع تقديم المساعدة الأساسية.\n\n" +
                "للحصول على إجابات ذكاء اصطناعي حقيقية، " +
                "سنربط التطبيق بواجهة AI في الخطوة التالية.";
    }

    // ============================================================
    // SUGGESTION
    // ============================================================

    private void addSuggestion(
            String buttonText,
            String question
    ) {

        Button button =
                actionButton(buttonText);

        LinearLayout.LayoutParams p =
                marginParams(
                        0,
                        4,
                        0,
                        4
                );

        contentContainer.addView(
                button,
                p
        );

        button.setOnClickListener(
                v -> {

                    chatMessages.add(
                            "👤 أنت:\n" +
                            question
                    );

                    chatMessages.add(
                            "🤖 BacTech AI:\n" +
                            generateLocalAnswer(
                                    question
                            )
                    );

                    showAI();
                }
        );
    }

    // ============================================================
    // SUBJECTS
    // ============================================================

    private void showSubjects() {

        clearContent();

        contentContainer.addView(
                title("📚 المواد الدراسية")
        );

        TextView sub =
                subtitle(
                        "اختر المادة التي تريد مراجعتها"
                );

        sub.setPadding(
                0,
                dp(5),
                0,
                dp(15)
        );

        contentContainer.addView(sub);

        addLargeSubject(
                "📐",
                "الرياضيات",
                "الدوال • الأعداد • الهندسة • الاحتمالات",
                PRIMARY
        );

        addLargeSubject(
                "⚡",
                "الفيزياء",
                "الكهرباء • الميكانيك • الطاقة",
                WARNING
        );

        addLargeSubject(
                "🧪",
                "الكيمياء",
                "التفاعلات • المحاليل • كمية المادة",
                SUCCESS
        );

        addLargeSubject(
                "⚙️",
                "التقنية",
                "الأنظمة • الكهرباء • الإلكترونيات",
                Color.rgb(180, 100, 240)
        );

        addLargeSubject(
                "🇫🇷",
                "الفرنسية",
                "فهم النص • القواعد • التعبير",
                Color.rgb(90, 180, 220)
        );

        addLargeSubject(
                "🇬🇧",
                "الإنجليزية",
                "Grammar • Vocabulary • Writing",
                Color.rgb(220, 100, 120)
        );
    }

    private void addLargeSubject(
            String icon,
            String name,
            String description,
            int accent
    ) {

        LinearLayout box =
                card();

        LinearLayout row =
                new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView iconView =
                new TextView(this);

        iconView.setText(icon);
        iconView.setTextSize(34);
        iconView.setGravity(Gravity.CENTER);

        row.addView(
                iconView,
                new LinearLayout.LayoutParams(
                        dp(60),
                        dp(60)
                )
        );

        LinearLayout info =
                new LinearLayout(this);

        info.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView n =
                new TextView(this);

        n.setText(name);
        n.setTextColor(TEXT);
        n.setTextSize(18);

        n.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView d =
                new TextView(this);

        d.setText(description);
        d.setTextColor(MUTED);
        d.setTextSize(12);

        info.addView(n);
        info.addView(d);

        row.addView(
                info,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        TextView arrow =
                new TextView(this);

        arrow.setText("›");
        arrow.setTextColor(accent);
        arrow.setTextSize(28);

        row.addView(
                arrow,
                new LinearLayout.LayoutParams(
                        dp(35),
                        dp(50)
                )
        );

        box.addView(row);

        contentContainer.addView(
                box,
                marginParams(
                        0,
                        6,
                        0,
                        6
                )
        );
    }

    // ============================================================
    // EXERCISES
    // ============================================================

    private void showExercises() {

        clearContent();

        contentContainer.addView(
                title("📝 التمارين")
        );

        TextView sub =
                subtitle(
                        "تدرب واختبر فهمك"
                );

        sub.setPadding(
                0,
                dp(5),
                0,
                dp(15)
        );

        contentContainer.addView(sub);

        addExerciseCard(
                "رياضيات",
                "الدوال",
                "مستوى متوسط",
                PRIMARY
        );

        addExerciseCard(
                "فيزياء",
                "دارة RC",
                "مستوى متوسط",
                WARNING
        );

        addExerciseCard(
                "كيمياء",
                "جدول التقدم",
                "مستوى متوسط",
                SUCCESS
        );

        addExerciseCard(
                "تقنية",
                "تحليل نظام",
                "مستوى متقدم",
                Color.rgb(180, 100, 240)
        );

        contentContainer.addView(
                sectionTitle(
                        "تمرين سريع"
                )
        );

        LinearLayout quick =
                card();

        TextView q =
                new TextView(this);

        q.setText(
                "إذا كان لديك تمرين في صورة، " +
                "يمكنك استخدامه مع المساعد الذكي."
        );

        q.setTextColor(TEXT);
        q.setTextSize(14);
        q.setGravity(Gravity.RIGHT);

        quick.addView(q);

        Button ai =
                actionButton(
                        "فتح المساعد 🤖"
                );

        LinearLayout.LayoutParams ap =
                marginParams(
                        0,
                        15,
                        0,
                        0
                );

        quick.addView(ai, ap);

        ai.setOnClickListener(
                v -> showAI()
        );

        contentContainer.addView(quick);
    }

    private void addExerciseCard(
            String subject,
            String exercise,
            String level,
            int accent
    ) {

        LinearLayout box =
                card();

        TextView s =
                new TextView(this);

        s.setText(
                subject
        );

        s.setTextColor(accent);
        s.setTextSize(12);

        s.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        box.addView(s);

        TextView e =
                new TextView(this);

        e.setText(exercise);
        e.setTextColor(TEXT);
        e.setTextSize(19);

        e.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        e.setPadding(
                0,
                dp(5),
                0,
                dp(4)
        );

        box.addView(e);

        TextView l =
                new TextView(this);

        l.setText(
                "⭐ " + level
        );

        l.setTextColor(MUTED);
        l.setTextSize(12);

        box.addView(l);

        Button start =
                actionButton(
                        "ابدأ التمرين"
                );

        LinearLayout.LayoutParams sp =
                marginParams(
                        0,
                        12,
                        0,
                        0
                );

        box.addView(
                start,
                sp
        );

        start.setOnClickListener(
                v -> showAI()
        );

        contentContainer.addView(
                box,
                marginParams(
                        0,
                        6,
                        0,
                        6
                )
        );
    }

    // ============================================================
    // PROGRESS
    // ============================================================

    private void showProgress() {

        clearContent();

        contentContainer.addView(
                title("📊 تقدمك")
        );

        TextView sub =
                subtitle(
                        "تابع تطور مستواك الدراسي"
                );

        sub.setPadding(
                0,
                dp(5),
                0,
                dp(18)
        );

        contentContainer.addView(sub);

        addProgressCard(
                "📚 الدروس",
                0
        );

        addProgressCard(
                "📝 التمارين",
                0
        );

        addProgressCard(
                "🤖 جلسات AI",
                0
        );

        LinearLayout goal =
                card();

        TextView goalTitle =
                new TextView(this);

        goalTitle.setText(
                "🎯 هدفك القادم"
        );

        goalTitle.setTextColor(TEXT);
        goalTitle.setTextSize(19);

        goalTitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        goal.addView(goalTitle);

        TextView goalText =
                new TextView(this);

        goalText.setText(
                "ابدأ بحل أول تمرين اليوم."
        );

        goalText.setTextColor(MUTED);
        goalText.setTextSize(14);

        goalText.setPadding(
                0,
                dp(8),
                0,
                0
        );

        goal.addView(goalText);

        contentContainer.addView(
                goal,
                marginParams(
                        0,
                        15,
                        0,
                        0
                )
        );
    }

    private void addProgressCard(
            String text,
            int value
    ) {

        LinearLayout box =
                card();

        TextView label =
                new TextView(this);

        label.setText(
                text + "     " + value + "%"
        );

        label.setTextColor(TEXT);
        label.setTextSize(15);

        box.addView(label);

        ProgressBar bar =
                new ProgressBar(
                        this,
                        null,
                        android.R.attr.progressBarStyleHorizontal
                );

        bar.setMax(100);
        bar.setProgress(value);

        LinearLayout.LayoutParams bp =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(8)
                );

        bp.topMargin =
                dp(12);

        box.addView(
                bar,
                bp
        );

        contentContainer.addView(
                box,
                marginParams(
                        0,
                        5,
                        0,
                        5
                )
        );
    }

    // ============================================================
    // SETTINGS
    // ============================================================

    private void showSettings() {

        clearContent();

        contentContainer.addView(
                title("⚙ الإعدادات")
        );

        TextView sub =
                subtitle(
                        "تحكم في إعدادات BacTech AI"
                );

        sub.setPadding(
                0,
                dp(5),
                0,
                dp(18)
        );

        contentContainer.addView(sub);

        addSetting(
                "🌙",
                "الوضع الداكن",
                "الواجهة الحالية تستخدم الوضع الداكن"
        );

        addSetting(
                "🔔",
                "الإشعارات",
                "تذكيرات المراجعة"
        );

        addSetting(
                "🎓",
                "المستوى الدراسي",
                "Bac Sciences Techniques"
        );

        addSetting(
                "🌐",
                "اللغة",
                "العربية"
        );

        contentContainer.addView(
                sectionTitle(
                        "حول التطبيق"
                )
        );

        LinearLayout about =
                card();

        TextView aboutText =
                new TextView(this);

        aboutText.setText(
                "BacTech AI\n\n" +
                "مساعد دراسي مخصص لطلاب البكالوريا.\n\n" +
                "الإصدار 1.0"
        );

        aboutText.setTextColor(TEXT);
        aboutText.setTextSize(14);
        aboutText.setGravity(Gravity.RIGHT);

        about.addView(aboutText);

        contentContainer.addView(about);
    }

    private void addSetting(
            String icon,
            String name,
            String description
    ) {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.HORIZONTAL
        );

        box.setGravity(
                Gravity.CENTER_VERTICAL
        );

        box.setPadding(
                dp(15),
                dp(13),
                dp(15),
                dp(13)
        );

        box.setBackground(
                background(SURFACE, 15)
        );

        TextView iconView =
                new TextView(this);

        iconView.setText(icon);
        iconView.setTextSize(25);
        iconView.setGravity(Gravity.CENTER);

        box.addView(
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

        TextView n =
                new TextView(this);

        n.setText(name);
        n.setTextColor(TEXT);
        n.setTextSize(16);

        n.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView d =
                new TextView(this);

        d.setText(description);
        d.setTextColor(MUTED);
        d.setTextSize(11);

        info.addView(n);
        info.addView(d);

        box.addView(
                info,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        Switch sw =
                new Switch(this);

        box.addView(sw);

        contentContainer.addView(
                box,
                marginParams(
                        0,
                        5,
                        0,
                        5
                )
        );
    }

    // ============================================================
    // BACK
    // ============================================================

    @Override
    public void onBackPressed() {

        if (currentPage != 0) {

            currentPage = 0;
            showHome();

        } else {

            super.onBackPressed();
        }
    }
}