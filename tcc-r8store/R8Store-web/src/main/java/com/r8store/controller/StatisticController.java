package com.r8store.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.r8store.enums.Enum_Gender;
import com.r8store.enums.Enum_Pages;
import com.r8store.model.dao.RatingDAO;


@Named
@ViewScoped
public class StatisticController extends Controller implements Serializable {
	
	private static final long serialVersionUID = 1443635620601894345L;
	
	@Inject
	private UserController userController;
	
	@Inject
	private RatingDAO rDAO;
	
	private BarChartModel areaModel;
	private BarChartModel barModel;
	
	private List<String> months;
	private String ano;
	
	@PostConstruct
	public void init() {
		ano = "2018";
		months = new ArrayList<String>();
		createAreaModel();
		createBarModelGeral();		
	}

	private void createBarModelGeral() {
		
		barModel = initBarModel();
		
		barModel.setTitle("Total Avaliações");
		barModel.setLegendPosition("ne");
		
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Mês-Ano");
		
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		
		yAxis.setMin(0);
		yAxis.setMax(5);
		
	}
	
	private BarChartModel initBarModel() {
		
		BarChartModel model = new BarChartModel();
		Object[] monthsArray = rDAO.findMonths(2018, userController.getStoreId());
		
		if (monthsArray.length > 0 && monthsArray[1] != null && monthsArray[0] != null) {
			int max = ((Integer) monthsArray[1]).intValue();
			int min = ((Integer) monthsArray[0]).intValue();
					
			for(int i = min; i <= max; i++) {
				months.add(i + "-" + ano);
			}
			
			ChartSeries estrelas01 = new ChartSeries();
			estrelas01.setLabel("1 Estrela");
			this.setByMonth(estrelas01, 1);
			
			ChartSeries estrelas02 = new ChartSeries();
			estrelas02.setLabel("2 Estrelas");
			this.setByMonth(estrelas02, 2);
			
			ChartSeries estrelas03 = new ChartSeries();
			estrelas03.setLabel("3 Estrelas");
			this.setByMonth(estrelas03, 3);
			
			ChartSeries estrelas04 = new ChartSeries();
			estrelas04.setLabel("4 Estrelas");
			this.setByMonth(estrelas04, 4);
			
			ChartSeries estrelas05 = new ChartSeries();
			estrelas05.setLabel("5 Estrelas");
			this.setByMonth(estrelas05, 5);
			
			model.addSeries(estrelas01);
			model.addSeries(estrelas02);
			model.addSeries(estrelas03);
			model.addSeries(estrelas04);
			model.addSeries(estrelas05);
		}		
					
		return model;
	}

	private void createAreaModel() {
        areaModel = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Masculino");
        
        int totalMas = rDAO.findByGender(Enum_Gender.MASCULINO, boys, userController.getStoreId());
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Feminino");
       
        int totalFem = rDAO.findByGender(Enum_Gender.FEMININO, girls, userController.getStoreId());
 
        areaModel.addSeries(boys);
        areaModel.addSeries(girls);
 
        areaModel.setTitle("Gráfico");
        areaModel.setLegendPosition("ne");
        
        Axis xAxis = areaModel.getAxis(AxisType.X);
        xAxis.setLabel("Mes-Ano");
         
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade");
        yAxis.setMin(0);
        yAxis.setMax(((totalFem + totalMas) / 2) + 5);
    }
	
	public void createStatisticPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.STATISTICPAGES)) {
			return;
		}	
	}
	
	public void setByMonth(ChartSeries estrelas, int starNum) {
		List<Object[]> rows = rDAO.findByMonth(starNum, this.userController.getStoreId());
		Map<String, Boolean> quantities = new HashMap<>();
		
		for (Object[] row: rows) {			
			estrelas.set(row[0] + "-" + row[1], ((Long) row[2]).intValue());
			quantities.put(row[0] + "-" + row[1], true);
		}
		
		for(String month : months) {
			if(quantities.get(month) == null || !quantities.get(month)) {
				estrelas.set(month, 0);
			}
		}
	
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public BarChartModel getAreaModel() {
		return areaModel;
	}

	public void setAreaModel(BarChartModel areaModel) {
		this.areaModel = areaModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

}
