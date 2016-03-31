package com.example.pcportablevidjay.financesnous;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import classes.MyDBHelper;

public class Fragment_Statistique extends Fragment {

    MyDBHelper myDBHelper = new MyDBHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_statistique, container, false);
        final CheckBox checkBoxGraphMois = (CheckBox) view.findViewById(R.id.checkBoxGraphMois);
        checkBoxGraphMois.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (checkBoxGraphMois.isChecked()) {
                    affichagetest(true, view.findViewById(R.id.dashboard_chart_mois));
                    graphDepenseMois();
                }
                else
                    affichagetest(false, view.findViewById(R.id.dashboard_chart_mois));
            }
        });

        final CheckBox checkBoxGraphDomaine = (CheckBox) view.findViewById(R.id.checkBoxGraphDomaine);
        checkBoxGraphDomaine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (checkBoxGraphDomaine.isChecked()) {
                    affichagetest(true, view.findViewById(R.id.dashboard_chart_domaine));
                    graphDepenseDomaine();
                }
                else
                    affichagetest(false, view.findViewById(R.id.dashboard_chart_domaine));
            }
        });

        return view;
    }

    private void affichagetest(final boolean show, final View mForm) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);

            mForm.setVisibility(show ? View.VISIBLE : View.GONE);
            mForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mForm.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mForm.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    private void graphDepenseMois(){
        int[] x = { 0,1,2,3,4,5,6,7,8,9,10,11,12 };
        double[] montantDepense = { janvier,fevrier,mars,avril,mai,juin,juillet,aout,septembre,octobre, novembre,decembre,tout };

        XYSeries barSeries = new XYSeries("Dépenses");

        for(int i=0;i<x.length;i++){
            barSeries.add(i,montantDepense[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(barSeries);

        XYSeriesRenderer bar = new XYSeriesRenderer();
        bar.setColor(Color.parseColor("#66d070"));
        bar.setFillPoints(true);
        bar.setChartValuesTextSize(20);
        bar.setDisplayChartValues(true);
        bar.setLineWidth((float) 0.5);

        XYMultipleSeriesRenderer graphBar = new XYMultipleSeriesRenderer();
        graphBar.setMarginsColor(Color.parseColor("#eff6ef"));
        graphBar.setXLabels(0);
        graphBar.setBarSpacing((float) 0.3);
        graphBar.setScale((float) 1);
        graphBar.setZoomButtonsVisible(true);
        graphBar.setAntialiasing(true);
        graphBar.setXAxisMin(-0.5);
        graphBar.setYAxisMin(0);
        graphBar.setXAxisMax(13);
        graphBar.setYAxisMax(1000);
        graphBar.setLabelsColor(Color.BLACK);
        graphBar.setLabelsTextSize(22);
        graphBar.setLegendTextSize(35);
        int[] margins = {70, 30, 50, 20};
        graphBar.setMargins(margins);
        for (int i = 0; i < x.length; i++) {
            graphBar.addXTextLabel(i, lesMois[i]);
        }
        graphBar.addSeriesRenderer(bar);

        GraphicalView chartViewMois;
        chartViewMois = ChartFactory.getBarChartView(getActivity().getBaseContext(), dataset, graphBar, BarChart.Type.DEFAULT);
        LinearLayout layoutMois = (LinearLayout) getActivity().findViewById(R.id.dashboard_chart_mois);
        layoutMois.addView(chartViewMois, new LayoutParams(960, LayoutParams.MATCH_PARENT));
    }

    private void graphDepenseDomaine(){
        String[] lesDomaines = new String[] {
                "Alimentation : "+alimentation+" €", "Articles d'habillement : "+habillement+" €", "Logement : "+logement+" €",
                "Jeux : "+jeux+" €", "Cadeaux : "+cadeau+" €", "Voiture : "+voiture+" €"
        };

        double[] montantDepense = { alimentation, habillement, logement, jeux, cadeau, voiture } ;

        int[] couleurs = {
                Color.parseColor("#66d070"), Color.parseColor("#C2F732"), Color.parseColor("#096A09"), Color.parseColor("#B0F2B6"), Color.GRAY, Color.BLACK
        };

        CategorySeries camentbertSeries = new CategorySeries(" Android version distribution as on October 1, 2012");
        for(int i=0; i < montantDepense.length; i++){
            camentbertSeries.add(lesDomaines[i], montantDepense[i]);
        }

        DefaultRenderer graphCamembert  = new DefaultRenderer();
        for(int i = 0 ; i < montantDepense.length ; i++){
            SimpleSeriesRenderer camenbert = new SimpleSeriesRenderer();
            camenbert.setColor(couleurs[i]);
            camenbert.getChartValuesSpacing();
            camenbert.getGradientStartValue();

            graphCamembert.addSeriesRenderer(camenbert);
        }

        graphCamembert.setZoomButtonsVisible(true);
        graphCamembert.setLabelsTextSize(20);
        graphCamembert.setShowLegend(false);
        graphCamembert.setLabelsColor(Color.BLACK);

        GraphicalView chartViewDomaine;
        chartViewDomaine = ChartFactory.getPieChartView(getActivity().getBaseContext(), camentbertSeries , graphCamembert);
        LinearLayout layoutDomaine = (LinearLayout) getActivity().findViewById(R.id.dashboard_chart_domaine);
        layoutDomaine.addView(chartViewDomaine, new LayoutParams(960, LayoutParams.MATCH_PARENT));
    }

    private final String[] lesMois = new String[] {
            "Janv.", "Fevier" , "Mars", "Avril", "Mai", "Juin", "Juillet", "Août" , "Sept.", "Oct.", "Nov.", "Dec.", "Total"
    };

    // Domaine
    private final double alimentation = Math.round(myDBHelper.getDepenseByDomaine(1)*100.0)/100.0;
    private final double habillement = Math.round(myDBHelper.getDepenseByDomaine(2)*100.0)/100.0;
    private final double logement = Math.round(myDBHelper.getDepenseByDomaine(3)*100.0)/100.0;
    private final double jeux = Math.round(myDBHelper.getDepenseByDomaine(4)*100.0)/100.0;
    private final double cadeau = Math.round(myDBHelper.getDepenseByDomaine(5)*100.0)/100.0;
    private final double voiture = Math.round(myDBHelper.getDepenseByDomaine(6)*100.0)/100.0;
    // Mois
    private final double janvier = Math.round(myDBHelper.getDepenseByMois("janvier")*100.0)/100.0;
    private final double fevrier = Math.round(myDBHelper.getDepenseByMois("fevrier")*100.0)/100.0;
    private final double mars = Math.round(myDBHelper.getDepenseByMois("mars")*100.0)/100.0;
    private final double avril = Math.round(myDBHelper.getDepenseByMois("avril")*100.0)/100.0;
    private final double mai = Math.round(myDBHelper.getDepenseByMois("mai")*100.0)/100.0;
    private final double juin = Math.round(myDBHelper.getDepenseByMois("juin")*100.0)/100.0;
    private final double juillet = Math.round(myDBHelper.getDepenseByMois("juillet")*100.0)/100.0;
    private final double aout = Math.round(myDBHelper.getDepenseByMois("aout")*100.0)/100.0;
    private final double septembre = Math.round(myDBHelper.getDepenseByMois("septembre")*100.0)/100.0;
    private final double octobre = Math.round(myDBHelper.getDepenseByMois("octobre")*100.0)/100.0;
    private final double novembre = Math.round(myDBHelper.getDepenseByMois("novembre")*100.0)/100.0;
    private final double decembre = Math.round(myDBHelper.getDepenseByMois("decembre")*100.0)/100.0;
    private final double tout = Math.round(myDBHelper.getDepenseByMois("tout")*100.0)/100.0;
}
