package GUI;

/*
 * Author: MohammadMehdi Shahmiri
 * Project: Access
 * Class Name: MyTable
 * 
 * Attributes:
 * 	tableModel			: our table DataModel
 * 	names				: column names
 * 	Datas				: each cell data
 * 	lastDataSearched	: need it to show the next Data
 * 	lastColumnSearched	: need it to show the next Data
 * 	id					: which rows are follow the filters
 * 	hit					: shows if there is any Data hit
 * 	rowsCount			: 
 * 	columnCount			: 
 * 	lastSearchFunc		: shows which function affected The id array!
 * 	timesCalled			: needed for finding the next data
 * 	lastCid				: for Scrolling to next Data
 * 
 * Functions:
 * 	Constructor/s:
 * 		MyTable(String[][] Data,String names):
 * 			initialization of some variables.
 * 	others:
 * 		searchWithSingleData(Data,Name):
 * 			Takes Data and column name and updates id array
 * 			 or going to next position!
 * 
 * 		scrollToVisible(RowID,ColumnID):
 * 			Scrolling to the needed row and column
 * 
 * 		ChangeData:
 * 		
 * 		
 *
 * Comments:
 * 	need to be in a JScrollPane.	
 * 
 * 	
 * */


import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable{
	private static final long serialVersionUID = 7498589304660475047L;
	DefaultTableModel tableModel;
	String[] names;
	String[][] Datas;
	String lastDataSearched,lastColumnSearched;
	
	boolean[] id;
	boolean hit;
	int rowsCount,columnCount,lastSearchFunc,timesCalled,lastCid,hitCount;
	
	
	public MyTable(String[][] Data,String[] names) {
		this.names = names;
		this.Datas = Data;
		rowsCount = Data.length;
		columnCount = names.length;
		
		//Creating Default model for items
		tableModel = new DefaultTableModel(Data, names){
			private static final long serialVersionUID = 3366311230198965306L;

			public boolean isCellEditable(int row,int column){
				return false;
			};
		};
		this.setModel(tableModel);
		
		//Disabling multiple selection
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	public void ChangeData(String[][] newData)
	{
		Datas = newData;
		this.setModel(new DefaultTableModel(newData,names));
	}
	
	
	//SearchFunction Just searches with one specific Data
	//SearchFuncID = 0x0001
	public boolean searchWithSingleData(String Data,String Cname) {
		int Cid = -1;
		
		//Checking lastSearchedFunc To show new Result
		if(lastSearchFunc == 0x0001)
		{
			int tmp;
			if(timesCalled == hitCount)
				timesCalled = 0;
			tmp = timesCalled;
			if(hit)
			{
				for(int i=0;i<rowsCount;i++)
					if(id[i])
					{
						if(tmp-- == 0)
						{
							this.setRowSelectionInterval(i,i);
							scrollToVisible(i,lastCid);
						}
					}
				timesCalled++;
				return true;
			}
		}
		else{
			//initialize every Specific value for this query
			lastCid = -1;
			id = new boolean[rowsCount];
			hit = false;
			timesCalled = 1;
			lastSearchFunc = 0x0001;
			lastDataSearched = Data;
			lastColumnSearched = Cname;
			hitCount = 0;
			
			//initialize id array
			for(int i=0;i<rowsCount;i++)
				id[i] = false;
			
			//SearchForColumn 
			for(int i=0;i<columnCount;i++)
				if(names[i].equalsIgnoreCase(Cname))
					Cid = i;
			lastCid = Cid;
			//SearchForRow
			if(Cid != -1)
				for(int i=0;i<rowsCount;i++)
					if(Datas[i][Cid].equalsIgnoreCase(Data))
					{
						hitCount++;
						id[i] = true;
						hit = true;
					}
			
			//which item shall be Selected
			if(hit)
			{
				for(int i=0;i<rowsCount;i++)
					if(id[i])
					{
						this.setRowSelectionInterval(i,i);
						scrollToVisible(i,lastCid);
						return true;
					}
			}
		}
		return false;
	}
	
	
	
	//this Function is from http://StackOverFlow.com/qusetions/853020
	public void scrollToVisible(int rowIndex, int vColIndex) {
        if (!(getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport)getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);

        scrollRectToVisible(rect);

        // Scroll the area into view
        viewport.scrollRectToVisible(rect);
    }
}

