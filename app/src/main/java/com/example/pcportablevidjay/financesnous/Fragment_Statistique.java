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

    public void affichagetest(final boolean show, final View mForm) {

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
        double[] depense = { janvier,fevrier,mars,avril,mai,juin,juillet,aout,septembre,octobre, novembre,decembre,tout };

        XYSeries depenseSeries = new XYSeries("Dépenses");

        for(int i=0;i<x.length;i++){
            depenseSeries.add(i,depense[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(depenseSeries);

        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.parseColor("#40A497"));
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setChartValuesTextSize(20);
        incomeRenderer.setDisplayChartValues(true);
        incomeRenderer.setLineWidth((float) 0.5);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setBarSpacing((float) 0.3);
        multiRenderer.setScale((float) 1);
        multiRenderer.setYTitle("Dépenses en euros");
        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setAntialiasing(true);
        multiRenderer.setXAxisMin(-0.5);
        multiRenderer.setYAxisMin(0);
        multiRenderer.setXAxisMax(13);
        multiRenderer.setYAxisMax(1000);
        // Size Text
        multiRenderer.setAxisTitleTextSize(25);
        multiRenderer.setChartTitleTextSize(40);
        multiRenderer.setLegendTextSize(60);
        // Margin
        int[] margins = {70, 30, 50, 20};
        multiRenderer.setMargins(margins);
        for (int i = 0; i< x.length;i++) {
            multiRenderer.addXTextLabel(i, lesMois[i]);
        }
        multiRenderer.addSeriesRenderer(incomeRenderer);

        GraphicalView chartViewMois;
        chartViewMois = ChartFactory.getBarChartView(getActivity().getBaseContext(), dataset, multiRenderer, BarChart.Type.DEFAULT);
        LinearLayout layoutMois = (LinearLayout) getActivity().findViewById(R.id.dashboard_chart_mois);
        layoutMois.addView(chartViewMois, new LayoutParams(960, LayoutParams.FILL_PARENT));
    }

    private void graphDepenseDomaine(){
        int[] values = { 1, 2, 10, 4, 5 };
        CategorySeries series = new CategorySeries("Pie Graph");
        int k = 0;
        for (int value : values) {
            series.add("Section " + ++k, value);
        }

        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };

        DefaultRenderer renderer = new DefaultRenderer();
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            r.setChartValuesTextSize(30);
            renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);

        GraphicalView chartViewDomaine;
        chartViewDomaine = ChartFactory.getPieChartView(getActivity().getBaseContext(), series, renderer);
        LinearLayout layoutDomaine = (LinearLayout) getActivity().findViewById(R.id.dashboard_chart_domaine);
        layoutDomaine.addView(chartViewDomaine, new LayoutParams(960, LayoutParams.FILL_PARENT));
    }

    private String[] lesMois = new String[] {
            "Janvier", "Fevier" , "Mars", "Avril", "Mai", "Juin", "Juillet", "Août" , "Sept.", "Octobre", "Nov.", "Dec.", "Total"
    };

    double janvier = Math.round(myDBHelper.getDepenseMois("janvier")*100.0)/100.0;
    double fevrier = Math.round(myDBHelper.getDepenseMois("fevrier")*100.0)/100.0;
    double mars = Math.round(myDBHelper.getDepenseMois("mars")*100.0)/100.0;
    double avril = Math.round(myDBHelper.getDepenseMois("avril")*100.0)/100.0;
    double mai = Math.round(myDBHelper.getDepenseMois("mai")*100.0)/100.0;
    double juin = Math.round(myDBHelper.getDepenseMois("juin")*100.0)/100.0;
    double juillet = Math.round(myDBHelper.getDepenseMois("juillet")*100.0)/100.0;
    double aout = Math.round(myDBHelper.getDepenseMois("aout")*100.0)/100.0;
    double septembre = Math.round(myDBHelper.getDepenseMois("septembre")*100.0)/100.0;
    double octobre = Math.round(myDBHelper.getDepenseMois("octobre")*100.0)/100.0;
    double novembre = Math.round(myDBHelper.getDepenseMois("novembre")*100.0)/100.0;
    double decembre = Math.round(myDBHelper.getDepenseMois("decembre")*100.0)/100.0;
    double tout = Math.round(myDBHelper.getDepenseMois("tout")*100.0)/100.0;
}
