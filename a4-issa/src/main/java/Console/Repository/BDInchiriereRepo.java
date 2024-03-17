package Console.Repository;

import Console.Domain.Inchiriere;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Console.Domain.generareInchirieri.generateInchiriereList;

public class BDInchiriereRepo extends MemoryRepo<Inchiriere>{

    private String JDBC_URL = "jdbc:sqlite:inchirieri.sqlite";

    Connection connection;

    Connection c;
    public BDInchiriereRepo(){
        openConnection();
        createTable();
        //initData(); //apelat o singura data
    }

    private void openConnection(){
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);

        try{
            if( connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable(){

        try(final Statement st = connection.createStatement()){
            st.execute("CREATE TABLE IF NOT EXISTS inchirieri(id int, idm int, datai varchar(255), dataf varchar(255));");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initData(){
        try( PreparedStatement st = connection.prepareStatement("INSERT INTO inchirieri VALUES (?,?,?,?)")){
            List<Inchiriere> inchiriereList = generateInchiriereList(100);

            // Print the generated list
            for (Inchiriere inchiriere : inchiriereList) {
                st.setInt(1,inchiriere.getId());
                st.setInt(2,inchiriere.getIdMasina());
                st.setString(3,inchiriere.getData_inceput());
                st.setString(4,inchiriere.getData_sfarsit());
                st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Inchiriere inchiriere){
        try( PreparedStatement st = connection.prepareStatement("INSERT INTO inchirieri VALUES (?,?,?,?)")){
            // Print the generated list
            st.setInt(1,inchiriere.getId());
            st.setInt(2,inchiriere.getIdMasina());
            st.setString(3,inchiriere.getData_inceput());
            st.setString(4,inchiriere.getData_sfarsit());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Inchiriere inchiriere){
        try( PreparedStatement st = connection.prepareStatement("UPDATE inchirieri SET idm=?, datai=?, dataf=? WHERE id=?;")){
            // Print the generated list
            st.setInt(4,inchiriere.getId());
            st.setInt(1,inchiriere.getIdMasina());
            st.setString(2,inchiriere.getData_inceput());
            st.setString(3,inchiriere.getData_sfarsit());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try( PreparedStatement st = connection.prepareStatement("DELETE FROM inchirieri WHERE id=?;")){
            st.setInt(1,id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Inchiriere> getAll(){
        ArrayList<Inchiriere> inchirieri = new ArrayList<>();
        try( PreparedStatement st = connection.prepareStatement("SELECT * FROM inchirieri;")){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                inchirieri.add(new Inchiriere(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inchirieri;
    }

}
