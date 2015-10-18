package pipetableformatter.operation;

import pipetableformatter.Range;
import pipetableformatter.TableDetector;

public abstract class Operation implements Runnable {
    protected TableText getSelectedTable(PipeTableEditor editor) {
        TableText tableText = editor.getSelectedText();
        if(tableText.isEmpty()) {
            String text = editor.getText();
            int caretPosition = editor.getCaretPosition();
            Range range = new TableDetector(text).findAround(caretPosition);
            tableText = new TableText(text.substring(range.getStart(), range.getEnd()), range);
        }
        return tableText;
    }
}