package pipetableformatter;

import java.util.ArrayList;
import java.util.List;

public class PipeTable {

    public static final String WIN_EOF = "\r\n";
    public static final String LINUX_EOF = "\n";
    List<PipeTableRow> table = new ArrayList<PipeTableRow>();
    int maxRowSize = 0;

    public PipeTable(String notFormattedText) {
        parseText(notFormattedText);
        normalizeRows();
    }

    private void normalizeRows() {
        for (PipeTableRow row : table) {
            for (int i = row.size(); i < maxRowSize; i++) {
                row.add("");
            }
        }
    }

    private void parseText(String notFormattedText) {

        LineSplitter lineSplitter = new LineSplitter(notFormattedText);

        do {
            parseLine(lineSplitter.nextLine(), lineSplitter.getEndOfLine());
        } while (lineSplitter.hasMoreLines());
    }

    private void parseLine(String line, String endOfLine) {
        PipeTableRow row = new PipeTableRow(splitForColumns(line), endOfLine);
        rememberMaxLength(row.size());
        table.add(row);
    }

    private List<String> splitForColumns(String line) {
        List<String> columns = new ArrayList<String>();

        ColumnSplitter columnSplitter = new ColumnSplitter(line);
        while(columnSplitter.hasValue()) {
            columns.add(columnSplitter.nextValue().trim());
        }
        return columns;
    }

    private void rememberMaxLength(int size) {
        if (size > maxRowSize) {
            maxRowSize = size;
        }
    }

    public int getRowCount() {
        return table.size();
    }

    public String getValue(int row, int column) {
        return table.get(row).get(column);
    }

    public PipeTableRow[] rows() {
        return table.toArray(new PipeTableRow[table.size()]);
    }

    public int getColumnCount() {
        return maxRowSize;
    }

}