package dataaccesslayer;

import transferobjects.UserAccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDAOImpl implements UserAccountDAO {

    @Override
    public void insertUserAccount(UserAccountDTO userAccount) throws SQLException {
        String sql = "INSERT INTO userAccount (id, balance, users_id) VALUES (?, ?, ?)";
        try (Connection con = DataSource.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, userAccount.getId());
            pstmt.setDouble(2, userAccount.getBalance());
            pstmt.setInt(3, userAccount.getUsersId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public UserAccountDTO getUserAccountById(int accountId) {
        String sql = "SELECT * FROM userAccount WHERE id = ?";
        UserAccountDTO account = null;
        try (Connection con = DataSource.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                account = new UserAccountDTO();
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersId(rs.getInt("users_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<UserAccountDTO> getAllUserAccounts() {
        List<UserAccountDTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM userAccount";
        try (Connection con = DataSource.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserAccountDTO account = new UserAccountDTO();
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersId(rs.getInt("users_id"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void updateUserAccount(UserAccountDTO userAccount) {
        String sql = "UPDATE userAccount SET balance = ?, users_id = ? WHERE id = ?";
        try (Connection con = DataSource.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, userAccount.getBalance());
            pstmt.setInt(2, userAccount.getUsersId());
            pstmt.setInt(3, userAccount.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserAccount(int accountId) {
        String sql = "DELETE FROM userAccount WHERE id = ?";
        try (Connection con = DataSource.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
