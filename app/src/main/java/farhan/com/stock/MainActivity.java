package farhan.com.stock;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import farhan.com.stock.Adapter.ProdukDataAdapter;
import farhan.com.stock.Fragment.ProductFragment;
import farhan.com.stock.Model.Produk;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import farhan.com.stock.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawer;
    NavigationView nvDrawer;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    public static ProdukDataAdapter dataproduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.naView);
        setupDrawer(nvDrawer);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        getSupportFragmentManager().beginTransaction().replace(R.id.konten, new ProductFragment()).commit();
        nvDrawer.getMenu().getItem(0).setChecked(true);
        setTitle("Produk");
        reqPerms();
        dataproduk = new ProdukDataAdapter(this, Produk.getInit(this));
    }

    private void reqPerms() {
        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    private void setupDrawer(NavigationView nview) {
        nview.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    public boolean onNavigationItemSelected(MenuItem menu) {
                        Terpilih(menu);
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_buka, R.string.drawer_tutup);
    }

    private void Terpilih(MenuItem menu) {
        Class fragclass;
        Fragment frag = null;
        switch (menu.getItemId()) {
            case R.id.frag1:
                fragclass = ProductFragment.class;
                break;
            case R.id.frag2:
                fragclass = LoginActivity.class;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            default:
                fragclass = ProductFragment.class;
        }
        try {
            frag = (Fragment) fragclass.newInstance();
        } catch (Exception e) {
        }
        if (frag != null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.konten, frag).commit();
            menu.setChecked(true);
            setTitle(menu.getTitle());
            mDrawer.closeDrawers();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: Implement this method
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {

    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Perijinan ditolak");
        dlg.setCancelable(false);
        dlg.setMessage("Untuk menggunakan Aplikasi ini kamu perlu membolehkan beberapa perijinan yang diajukan. Atau Aplikasi ini tidak bisa digunakan");
        dlg.setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                MainActivity.this.finish();
            }
        });
        dlg.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
