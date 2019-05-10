package g.oa.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import g.oa.App;
import g.oa.bean.Record;
import g.oa.service.RecordService;
import g.oa.util.DateUtil;
import g.oa.util.PropertyUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RecordController implements Initializable {
	@FXML
	BorderPane borderPane;
	@FXML
	AnchorPane anchorPane;
	@FXML
	private TextField id;
	@FXML
	private TextField title;
	@FXML
	private TextArea content;
	@FXML
	private ComboBox<String> type;
	@FXML
	private TextField searchTitle;
	@FXML
	private TextField searchContent;
	@FXML
	private DatePicker searchDateFrom;
	@FXML
	private DatePicker searchDateTo;
	@FXML
	private ComboBox<String> searchType;
	@FXML
	private TableView<Record> tvRecord;
	@FXML
	private TableColumn<Record, String> tcId;
	@FXML
	private TableColumn<Record, String> tcTitle;
	@FXML
	private TableColumn<Record, String> tcType;
	@FXML
	private TableColumn<Record, String> tcDate;
	@FXML
	private TableColumn<Record, String> tcContent;
	@FXML
	private Pagination pagination;
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem about;
	@FXML
	private MenuItem exit;
	@FXML
	private MenuItem cache;

	private static final String INCOMPLETE_INFORMATION = "信息不完整";
	private static final String CANNOT_SAVE = "无法保存";
	private static final String CANNOT_SEARCH = "无法查询";
	private static final int PAGE_SIZE = 9;
	private RecordService service = new RecordService();

	@FXML
	public void resetInfo() {
		title.setText("");
		type.getSelectionModel().select(0);
		content.setText("");
		id.setText("");
	}

	@FXML
	public void search(ActionEvent event) {
		loadTvRecord();
		pagination.setCurrentPageIndex(0);
	}

	@FXML
	public void save(ActionEvent event) {
		Record r = getRecord(InfoType.info);
		boolean isUpdata = false;
		if (id.getText() != null && !id.getText().equals("")) {
			isUpdata = true;
			r.setId(Long.parseLong(id.getText()));
		}
		if (isUpdata)
			service.update(r);
		else
			service.save(r);

		resetInfo();
		loadTvRecord(pagination.getCurrentPageIndex());
	}

	@FXML
	public void remove(ActionEvent event) {
		List<Record> rs = tvRecord.getSelectionModel().getSelectedItems();
		for (Record r : rs)
			service.delete(r);
		resetInfo();
		loadTvRecord(pagination.getCurrentPageIndex() + 1);
	}
	
	private Record getRecord(InfoType infoType) {
		String f = searchDateFrom.getValue() == null
				? null
				: searchDateFrom.getValue().toString().replaceAll("-", "");
		String t = searchDateTo.getValue() == null
				? null
				: searchDateTo.getValue().toString().replaceAll("-", "");
		Record r = new Record();
		r.setDate(DateUtil.currentYMDHS());
		String iTitle = "";
		String iType = "";
		String iContent = "";
		switch (infoType) {
			case search :
				iTitle = searchTitle.getText();
				iType = searchType.getValue();
				iContent = searchContent.getText();
				break;
			case info :
				iTitle = title.getText();
				iType = type.getValue();
				iContent = content.getText();
				break;
			default :
				break;
		}
		if (iTitle != null)
			r.setTitle(iTitle);
		if (iType != null)
			r.setType(iType);
		if (iContent != null)
			r.setContent(iContent);
		if (f != null)
			r.setFromDate(f);
		if (t != null)
			r.setToDate(t);
		return r;
	}

	private void setInfo(Record record) {
		title.setText(record.getTitle());
		content.setText(record.getContent());
		type.setValue(record.getType());
		id.setText(String.valueOf(record.getId()));
	}

	private void showAlert(String info, String title, String header) {
		Alert information = new Alert(Alert.AlertType.INFORMATION, info);
		information.setTitle(title);
		information.setHeaderText(header);
		information.showAndWait();
	}

	private TableView<Record> loadTvRecord() {
		return loadTvRecord(null, 1);
	}

	private TableView<Record> loadTvRecord(int pageNum) {
		return loadTvRecord(null, pageNum);
	}

	private TableView<Record> loadTvRecord(List<Record> recordList,
			int pageNum) {
		PageHelper.startPage(pageNum, PAGE_SIZE);
		if (recordList == null)
			recordList = service.search(getRecord(InfoType.search));
		ObservableList<Record> list = FXCollections
				.observableArrayList(recordList);

		tcTitle.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<Record, String> param) {
						return new SimpleStringProperty(
								param.getValue().getTitle());
					}
				});
		tcType.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<Record, String> param) {
						return new SimpleStringProperty(
								param.getValue().getType());
					}
				});
		tcDate.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<Record, String> param) {
						return new SimpleStringProperty(
								param.getValue().getShowDate());
					}
				});
		tcContent.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<Record, String> param) {
						return new SimpleStringProperty(
								param.getValue().getContent());
					}
				});
		tvRecord.setItems(list);
		setPagination(((Page<Record>) recordList).getPages());
		return tvRecord;
	}

	private void initTvRecord() {
		tvRecord.setTableMenuButtonVisible(true);
		tvRecord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tvRecord.setRowFactory(
				new Callback<TableView<Record>, TableRow<Record>>() {
					@Override
					public TableRow<Record> call(TableView<Record> param) {
						return new TvRecordRowControl();
					}
				});
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer PageNum) {
				return loadTvRecord(PageNum + 1);
			}
		});
	}

	private void setPagination(int pageCount) {
		pagination.setMaxPageIndicatorCount(10);
		pagination.setPageCount(pageCount);
	}

	class TvRecordRowControl extends TableRow<Record> {
		public TvRecordRowControl() {
			super();
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Record r = tvRecord.getSelectionModel().getSelectedItem();
					setInfo(r);
				}
			});
		}
	}

	private void initMenu() {
		about.setOnAction((ActionEvent t) -> {
			about();
		});
		exit.setOnAction((ActionEvent t) -> {
			Stage stage = (Stage) anchorPane.getScene().getWindow();
			stage.close();
		});
		cache.setOnAction((ActionEvent t) -> {
			cache();
		});
	}
	private static final String TYPE_KEY="typeOblist";
	private void cache() {
		String v = addCache();
		Properties prop = PropertyUtil.loadCacheProperties();
		String p = prop.getProperty(TYPE_KEY);
		if(p!=null) {
			p+=","+v;
			PropertyUtil.storeCacheProperties(TYPE_KEY, p, false);
		}else {
			PropertyUtil.storeCacheProperties(TYPE_KEY, v, false);
		}
	}
	
	private String addCache() {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("类型");
		dialog.setHeaderText("添加类型");
		ButtonType buttonType = new ButtonType("完成", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		TextField value = new TextField();
		value.setPromptText("Value");
		grid.add(new Label("Value:"), 0, 1);
		grid.add(value, 1, 1);
		dialog.getDialogPane().setContent(grid);
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == buttonType) {
		        return value.getText();
		    }
		    return null;
		});
		Optional<String> result = dialog.showAndWait();
		String[] cacheVal = {""};
		result.ifPresent(val -> {
			cacheVal[0] =val;
		});
		return cacheVal[0];
	}
	private static final String aboutInfo = "..................................................\r\nHello! \r\nI'm G, and this is just a exercise.\r\n..................................................";
	private void about() {
		Dialog<Boolean> dialog = new Dialog<>();
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getClassLoader()
				.getResource("images/LOGO.gif").toExternalForm()));
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText(aboutInfo);
		Image logo = new Image(getClass().getClassLoader()
				.getResource("images/LOGO_Main.png").toExternalForm(), 400, 300,
				false, false);
		dialog.setGraphic(new ImageView(logo));
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CountDownLatch latch = new CountDownLatch(1);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			service.initDBTable();
			latch.countDown();
		});
		executor.execute(() -> {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			searchDateFrom.setValue(LocalDate.parse(DateUtil.firstDayCurrentOfWeek()));
			searchDateTo.setValue(LocalDate.now());
			type.setItems(App.TYPE_OBLIST);
			type.getSelectionModel().select(0);
			searchType.setItems(App.TYPE_OBLIST);
			initMenu();
			javafx.application.Platform.runLater(() -> {
				initTvRecord();
			});
		});
	}
	
	private static enum InfoType {
		search, info
	}
}
